; Sean Gordon
; skgordon #1405355
; Lab 6: Caesar Cipher
; CMPE 12L Section 7 (T-Th 3pm)
; Lab6.asm
; Due 3/6/16
;
	.ORIG x3000
	LEA R0, Welc
	PUTS
Begin	LEA R0, Prompt 	; Load Prompt
	PUTS		; Display Prompt

Welc	.STRINGZ "Hello, welcome to my Caesar Cipher program"
Prompt	.STRINGZ "\nDo you want to (E)ncrypt or (D)ecrypt or e(X)it?\n>"

	AND R2, R2, #0
	AND R3, R3, #0
	AND R4, R4, #0
	AND R5, R5, #0	; Clear everything
	AND R6, R6, #0
	AND R7, R7, #0
Input	AND R0, R0, #0
	AND R1, R1, #0	
	GETC
	ADD R1, R1, R0	; Store input in R1
	OUT
	NOT R1, R1
	ADD R1, R1, #1	; two's comp to check
	LD R3, LetX
	ADD R2, R1, R3
	BRz ExitL	; Check for 'X' - exit

LetX	.FILL x58
CiphP	.STRINGZ "\nWhat is the cipher (1-25)?\n>"

CiphNum	AND R2, R2, #0
	LEA R0, CiphP
	PUTS
DigIn	AND R0, R0, #0	; Get the Cipher Digit
	GETC
	OUT
	NOT R0, R0
	ADD R0, R0, #1
	LD R3, LF
	ADD R5, R0, R3
	BRz CiphB	; Check for Return key
	ADD R0, R0, #-1
	NOT R0, R0
	AND R3, R3, #0
	LD R3, DigCt
DigOff	ADD R0, R0, #-12 ; Calculate actual
	ADD R3, R3, #-1	; Digit value
	BRp DigOff
	
	AND R3, R3, #0
	LD R3, IntCt
	AND R4, R4, #0
	ADD R4, R4, R2
IntTen	ADD R2, R2, R4	; moves digits over
	ADD R3, R3, #-1	; for new digit typed
	BRp IntTen
	ADD R2, R2, R0	; add new digit
	BR DigIn

Test	ST R7, ST7
	AND R3, R3, #0
	LD R7, NotA	
	ADD R7, R7, R4
	BRn Ch1		; Flags if below 'A'
	LD R7, NotZ
	ADD R7, R7, R4
	BRnz Go		; If between A and Z, no flag
	LD R7, Nta
	ADD R7, R7, R4
	BRn Ch1		; Flags if lower than 'a'
	LD R7, Ntz
	ADD R7, R7, R4
	BRnz Go		; If lower than 'z' no flag
Ch1	ADD R3, R3, #1
Go	LD R7, ST7
	RET

TSE	.FILL xFFE6
Sv4	.FILL #0
OverE	ST R7, ST7
	ST R4, Sv4
	LD R1, TSE
	LD R7, NotZ
	ADD R7, R7, R4
	BRnz UpE	; Upper Case?
	ADD R4, R2, R4	; Put in the cipher
	LD R7, Ntz
	ADD R7, R7, R4
	BRp FlagE	; If it goes over z, flag it
	BR FinE		; Otherwise, its fine
UpE	ADD R4, R2, R4	; Put in the cipher
	LD R7, NotZ
	ADD R7, R7, R4
	BRp FlagE
	BR FinE		; If it goes over Z, flag it
FlagE	ADD R2, R2, R1	; Subtract 26 from the cipher
FinE	LD R4, Sv4
	LD R7, ST7
	RET
	

ExitL	AND R0, R0, #0
	LEA R0, ExitP	; Exits program
	PUTS
	HALT
ExitP	.STRINGZ "\nGoodbye!"

CiphB	ST R2, Ciph	; Store the cipher
 	LEA R0, StrP	; Prompt for the input string
	PUTS
	LEA R3, Arr
StrB	AND R0, R0, #0
	GETC
	OUT
	NOT R0, R0
	ADD R0, R0, #1
	LD R4, LF
	ADD R7, R0, R4
	BRz Next	; Check for Return key
	ADD R0, R0, #-1
	NOT R0, R0
	STR R0, R3, #0	; Store the character in the array
	ADD R3, R3, #1
	BR StrB		; Get the next character
Next	LD R7, LetE
	ADD R7, R1, R7
	BRz EncL	; Check for (E)ncrpyt
	LD R7, LetD
	ADD R7, R1, R7
	BRz DecL	; Check for (D)ecrypt
EncL	JSR Encrypt	; Encrypt the Array
	BR Pr
DecL	JSR Decrypt	; Decrypt the Array
Pr	JSR PrintA	; Print the Array
	JSR ClrArr	; Clear the array
	BR Begin	; Reprompt the user

StrP	.STRINGZ "What is the string (up to 200 characters)?\n>"

TSD	.FILL x1A
OverD	ST R7, ST7
	ST R4, Sv4
	LD R1, TSD
	LD R7, NotZ
	ADD R7, R7, R4
	BRnz UpD	; Upper Case?
	ADD R4, R2, R4	; Subtract the cipher
	LD R7, Nta
	ADD R7, R7, R4
	BRn FlagD	; lower than 'a'? flag it
	BR FinD
UpD	ADD R4, R2, R4	; subtract the cipher
	LD R7, NotA
	ADD R7, R7, R4	; lower than 'A'? flag it
	BRn FlagD
	BR FinD
FlagD	ADD R2, R2, R1	; Add 26 to the cipher
FinD	LD R4, Sv4
	LD R7, ST7
	RET


Encrypt	ST R7, Save7
	AND R5, R5, #0
	LEA R0, Arr	; Load the array
EnLp	LD R2, Ciph	; Reload the cipher
	JSR Load	; Bring in the new character
	AND R7, R7, #0
	ADD R7, R4, R7
	BRz DonE	; If the string is done (x0000) stop
	JSR Test	; See if its a non alphabet character
	AND R7, R7, #0
	ADD R7, R3, R7
	BRp SkipE	; Skip if its non-alpha
	JSR OverE	; Check for cipher adding overflow
	ADD R6, R4, R2	; Add in the cipher
	JSR Store	; Store the new character
	ADD R5, R5, #1	; Increment the character pointer
	BR EnLp		; Loop to next character
SkipE	ADD R6, R4, #0	; Don't add cipher
	JSR Store	; Store the same character
	ADD R5, R5, #1	; Increment the character pointer
	BR EnLp	
DonE	LD R7, Save7
	RET

Decrypt	ST R7, Save7
	AND R5, R5, #0
	LEA R0, Arr	; Load array
DeLp	LD R2, Ciph
	NOT R2, R2
	ADD R2, R2, #1	; Negate cipher for subtractions
	JSR Load	; Load new character into R4
	AND R7, R7, #0
	ADD R7, R4, R7
	BRz DonD	; Check for end of string (x0000)
	JSR Test	; Test for non-alphabet characters
	AND R7, R7, #0
	ADD R7, R3, R7
	BRp SkipD	; Skip it if it's flagged as non-alpha
	JSR OverD	; Check for overflow of cipher subtraction
	ADD R6, R4, R2	; Subtract the cipher
	JSR Store	; Store new character
	ADD R5, R5, #1	; Increment character pointer
	BR DeLp
SkipD	ADD R6, R4, #0	; Don't subtract cipher
	JSR Store
	ADD R5, R5, #1
	BR DeLp
DonD	LD R7, Save7
	RET

Ciph	.FILL #0
LF	.FILL xA
LetE	.FILL x45
LetD	.FILL x44
DigCt	.FILL #4
IntCt	.FILL #9
PrntCt	.FILL #19
Save7	.FILL #0
ST7	.FILL #0
SS7	.FILL #0
SL7	.FILL #0
NotA	.FILL xFFBF
NotZ	.FILL xFFA6
Nta	.FILL xFF9F
Ntz	.FILL xFF86


Store	ST R7, SS7
	LEA R0, Arr	; Load Array
	LD R7, PrntCt
Row1	ADD R0, R0, #10 ; Add 10
	ADD R7, R7, #-1	; 20 times
	BRp Row1	; This is Row Major
	ADD R0, R0, R5	; Add the current character position
	STR R6, R0, #0	; Store new character in position
	LD R7, SS7
	RET

Load	ST R7, SL7
	LEA R0, Arr	; Load Array
	ADD R0, R0, R5	; Add the current character position
	LDR R4, R0, #0	; Load new character into R4
	LD R7, SL7
	RET

ClrArr	ST R7, Save7
	LEA R3, Arr
	LD R7, ClrCt
Cloop	AND R0, R0, #0
	STR R0, R3, #0	; Store x0 in current array spot
	ADD R3, R3, #1	; Go to next array location
	ADD R7, R7, #-1	; Decrement counter
	BRp Cloop	; Do it 400 times
	LD R7, Save7
	RET
ClrCt	.FILL #399

PrintA	ST R7, Save7
	LEA R0, ResP
	PUTS
	LEA R0, InP	; Display Prompts for user
	PUTS
	LEA R0, Arr	; Display their original
	PUTS		; Input
	LEA R0, OutP
	PUTS
	LEA R0, Arr
	LD R2, PrntCt
Loop	ADD R0, R0, #10 ; Add 10
	ADD R2, R2, #-1	; 20 times
	BRp Loop	; This is Row Major
	PUTS		; Display their (en/de)crypted result
	LD R7, Save7
	RET

ResP	.STRINGZ "Here is your string and the result"
InP	.STRINGZ "\n<Input>  "
OutP	.STRINGZ "\n<Result> "

Arr	.BLKW 400
;
	.END
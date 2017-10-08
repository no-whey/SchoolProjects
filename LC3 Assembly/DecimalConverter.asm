; Sean Gordon
; skgordon #1405355
; Lab 5: Decimal Converter
; CMPE 12L Section 7 (T-Th 3pm)
; Lab5.asm
; Due 2/28/16
;
	.ORIG x3000
	LEA R0, Welc
	PUTS
Begin	LEA R0, Prompt 	; Load Prompt
	PUTS		; Display Prompt

	AND R2, R2, #0
	AND R3, R3, #0
	AND R4, R4, #0
	AND R5, R5, #0	; Clear everything
	AND R6, R6, #0
	AND R7, R7, #0
Input	AND R0, R0, #0
	AND R1, R1, #0	
	GETC
	ADD R1, R1, R0	; Store digit in R1
	OUT
	NOT R1, R1
	ADD R1, R1, #1	; two's comp to check
	LD R3, ExitX
	ADD R2, R1, R3
	BRz ExitL	; Check for 'X' - exit
	LD R3, LF
	ADD R2, R1, R3
	BRz LFL		; Check for Return key
	LD R3, Signed
	ADD R2, R1, R3
	BRz IsNeg	; Check for Negative
			; Sign

	ADD R1, R1, #-1
	NOT R1, R1
	AND R3, R3, #0
	LD R3, DigCt
DigOff	ADD R1, R1, #-12 ; Calculate actual
	ADD R3, R3, #-1	; Digit value
	BRp DigOff
	
	AND R3, R3, #0
	LD R3, IntCt
	AND R4, R4, #0
	ADD R4, R4, R5
IntTen	ADD R5, R5, R4	; moves digits over
	ADD R3, R3, #-1	; for new digit typed
	BRp IntTen
	ADD R5, R5, R1	; add new digit
	BR Input

IsNeg	AND R6, R6, #0
	ADD R6, R6, #1	; flag's a negative
	BR Input	; number

LFL	LEA R0, Thank
	PUTS
	AND R0, R0, #0
	ADD R6, R6, #0
	BRz NotNeg	; Checks negative flag
	NOT R5, R5	; two's comp if
	ADD R5, R5, #1	; negative flagged
NotNeg	AND R2, R2, #0
	AND R3, R3, #0
	AND R1, R1, #0

	LD R2, count
	LEA R1, MASK	; Load mask
	LDR R3, R1, #0	; Load mask initial state
Loop	ADD R2, R2, #0
	BRn Begin	; checks digit tracker
	LDR R3, R1, #0	; Load new mask state
	AND R4, R4, #0
	AND R4, R5, R3	; Check bit for value
	BRz PrntZ
	AND R0, R0, #0
	LD R0, ONE	; Prints a 1
	OUT
Back	ADD R2, R2, #-1	; increment counter
	ADD R1, R1, 1	; Change mask state
	BR Loop
	
PrntZ	AND R0, R0, #0
	LD R0, ZERO	; Prints a 0
	OUT
	BR Back

ExitL	LD R0, LF
	OUT
	AND R0, R0, #0
	LEA R0, ExitP	; Exits program
	PUTS
	HALT

Welc	.STRINGZ "Welcome to the conversion program"
Prompt	.STRINGZ "\nEnter a decimal number or X to quit:\n>"
Thank	.STRINGZ "Thanks, here it is in binary\n"
ExitP	.STRINGZ "Bye. Have a great day."
ONE	.FILL x31
ZERO	.FILL x30
LF	.FILL xA
Signed	.FILL x2D
ExitX	.FILL x58
count	.FILL #15
DigCt	.FILL #4
IntCt	.FILL #9
MASK	.FILL x8000
	.FILL x4000
	.FILL x2000
	.FILL x1000
	.FILL x0800
	.FILL x0400
	.FILL x0200
	.FILL x0100
	.FILL x0080
	.FILL x0040
	.FILL x0020
	.FILL x0010
	.FILL x0008
	.FILL x0004
	.FILL x0002
	.FILL x0001
	.END
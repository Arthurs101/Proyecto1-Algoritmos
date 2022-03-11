;Universidad del Valle de Guatemala
;Algoritmos y Estructura de datos
;Catedrático: Moises Alonso
;Tercer Semestre 2022
;Grupo 1:
;Arturo Argueta: 21527
;Astrid Glauser: 21299
;Abner Garcia: 21285
;Gonzalo Santizo: 21504
;Sección 20
;Actividad: Proyecto 1 Fase 1

; Aqui se convierte el numero colocado a factorial
(defun factorial (num) ; factorial
    (if (= num  0) 1
        (setq fact (* num (factorial(- num 1))))
    ) 
)

;Aqui Es para convertir el numero que el usuario ingreso a una serie de fibbonaci
(defun fibonacci (n) ;fibbonaci
  (cond 
    ( (< n 2) n); si es 0 o 1 regresa el valor del numero dado que el resultado es 0 y 1 en la serie de fibonacci respectivamente
    (t (+ (fibonacci (- n 1)) (fibonacci (- n 2)))) ;de lo contrario evaluar el resultado de fibonacci de n-1 y de n-2, donde n es el numero ingresado
    )
)

;Aqui convertira el numero de celcius a Farengeint
(defun converter(num) ; C a F 
    (+ ( * (* 1.8) num) 32)
)

;Aqui convertira el numero de Farengeint a celcius
(defun far (num) ;F a C
    (* (- num 32) (* 0.555555556))
)
(print "**************Bienvenidos al programa, es un gusto tenerte de regreso :)*************")
(print "Tu numero colocado se convertira en lo siguiente")
(print "---Factorial")
(print "---Serie Fibonacci")
(print "---De celsius a farengeint")
(print "---De farengeint a celsius")

(print "Porfavor, Coloque el numero que desee convertir")
(defvar number (read))
(print "")
(print "--------------------------")
(print "Tu numero colocado en factorial Es: ")
(print (factorial number))
(print "")
(print "--------------------------")
(print "Tu numero en la serie de fibonacci Es: ")
(print(fibonacci number))
(print "")
(print "--------------------------")
(print  "Tu numero convertido de celsius a farengeint es") 
(print (converter number))
(print " ")
(print "--------------------------")
(print "Tu numero convertido de farengeiht a caelcius es") 
(print (far number))
import java.util.Scanner;

public class Main {
    public static void main(String[] args) { // He añadido el String[] args para que compile
        Scanner scn = new Scanner(System.in);
        int [] numerosUsuario= new int[5];
        // numeroUsuario = [0, 0, 0, 0, 0]

        //Mensaje informatvvo del sistema
        System.out.println("Indique los 5 numeros con los que quiere jugar");

        // Bucle Principal: Recorre las 5 posiciones (i)
        for (int i = 0; i < numerosUsuario.length; i++) { // [1] Inicio Vuelta

            int candidato; // variable que vamos a usar para comparar
            boolean valido; // Bandera verde(es un booleano true que va a hacer que no se repita el while mientras sea true)

            // BUCLE DE VALIDACIÓN:
            // "Pide el dato y repite MIENTRAS NO sea válido"
            do { // [2] Entrada al Do-While
                System.out.printf("Indique el numero %d%n", (i + 1));
                candidato = scn.nextInt(); // [3] Usuario escribe aquí

                // 1. ESTRATEGIA OPTIMISTA:(Ponemos el boolean en positivo y comprobamos el fallo.
                // Al empezar cada intento, asumimos que el número es bueno (Verde).
                // Si no ponemos esto a true aquí, se quedaría en false para siempre tras un error.
                valido = true; // [4] Semáforo Verde (Reset)

                // 2. Comprobamos si ya existe en los anteriores
                for (int j = 0; j < i; j++) { // [5] Inspector J revisa historial
                    if (candidato == numerosUsuario[j]) { //Aqui comparamos // [6] ¿Coincide?
                        //Mensaje infortivo del sistema avisandonos que ese numero esta reptido
                        System.out.println(" Error: El número " + candidato + " ya lo has puesto. Prueba otro.");

                        // ¡Fallo! Bajamos la bandera a Rojo.
                        valido = false;// [7] ¡CULPABLE! // Aqui hemos cambiado el boolean anterior a false entonces la condicion del while se cumple(repetimos do-while)
                        break; // Dejamos de mirar, ya sabemos que está mal.
                    }
                }

                // 3. CONDICIÓN: (!valido) significa "Si valido es false -> Repite"
            } while (!valido); // [8] Decisión: ¿Repetimos? (hemos puesto !valido que es igual que decir valido = false)

            // Si el programa llega aquí, es que el 'do-while' ha terminado.
            // Significa que 'valido' sigue siendo true. ¡Guardamos!
            numerosUsuario[i] = candidato; // [9] Guardado Oficial
        }

        // Mostramos el resultado final haciendo un bucle para que imprima todaslas posiciones de numeroUsuario
        System.out.println("El boleto del usuario es: ");
        for (int item : numerosUsuario) {
            System.out.println(item);
        }
    }
}




/*He preparado este formato tipo "Tarjeta de Ejecución".
A la izquierda ves el código que se está ejecutando en ese momento,
y a la derecha lo que pasa en la memoria.

🎬 ESCENARIO DE LA SIMULACIÓN

Objetivo: Rellenar el array numerosUsuario de 5 huecos.

Entrada del Usuario: 5, 8, 5 (Repetido), 9.

Estado Inicial: [ 0, 0, 0, 0, 0 ]


🔵 VUELTA 1: Rellenando la Casilla 0 (i=0)

Código ejecutándose,             Qué pasa en la Memoria

for (int i = 0; ...),            INICIO: La variable i vale 0. Estamos apuntando al primer hueco.
do {,                            Entramos en la zona de validación.
candidato = scn.nextInt();,⌨️    El usuario escribe un 5.  Variable candidato = 5.
valido = true;,                  BANDERA VERDE (boolean true)
for (int j = 0; j < i; j++),     INSPECTOR:En la primera vuelta como j(0) < i(0) es falso, el inspector NO trabaja.No hay números anteriores."
while (!valido);                 While se repetira siempre que valido = false, como en este caso es valido=true
✅ Salimos del bucle de validación.
numerosUsuario[i] = candidato;    💾 GUARDADO: Metemos el 5 en la posición 0.
Estado del Array:                 "[ 5, 0, 0, 0, 0 ]"



🔵 VUELTA 2: Rellenando la Casilla 1 (i=1)
Código ejecutándose	                Qué pasa en la Memoria

i++	                                La variable i avanza a 1. Apuntamos al segundo hueco.
do {	                            Entramos en la zona de validación.
candidato = scn.nextInt();          ⌨️ El usuario escribe un 8.
Variable candidato = 8.

valido = true;	                    BANDERA VERDE (boolean true) en la siguiente vuelta en el reintento veremos porque este punto es importante
for (int j = 0; j < i; j++)	        INSPECTOR (j=0): Compara candidato (8) vs numeros[0] (5).
if (candidato == numeros[j])	    ¿Son iguales? NO. El inspector no encuentra problemas.
while (!valido);                     ¿Es inválido? NO.

✅ Salimos del bucle.

numerosUsuario[i] = candidato;	      💾 GUARDADO: Metemos el 8 en la posición 1.
Estado del Array:	                  [ 5, 8, 0, 0, 0 ]



🔴 VUELTA 3: Rellenando la Casilla 2 (i=2) - ¡EL ERROR! 🚨
Aquí es donde el código usa el while y repite porque el dato no es valido


Código ejecutándose	                    Qué pasa en la Memoria
i++	                                    La variable i avanza a 2. Apuntamos al tercer hueco.
do {	                                Entramos en la validación.
candidato = scn.nextInt();              ⌨️ El usuario escribe un 5.
Variable candidato = 5.

valido = true;	                        🟢 BANDER VERDE (Optimismo inicial).
for (int j = 0; j < i; j++)	            INSPECTOR (j=0): Compara candidato (5) vs numeros[0] (5). Recordamos que ahora mismo el array es [ 5, 8, 0, 0, 0 ] y nosotros le hemos introducido [ 5, 8, 5, 0, 0 ]

if (candidato == numeros[j])	         ¿Son iguales? ¡SÍ! 💥 Conflicto detectado.
valido = false;	                         🔴 ACCIÓN: Bajamos el semáforo a ROJO.
break;	                                 🛑 ACCIÓN: El inspector deja de trabajar inmediatamente. Para que queremos seguir recorriendo numeros si ya hemos encontrado fallo,
while (!valido);                         La condición es: "Mientras NO sea válido" (mientras valido = false)... como ahora se cumple...

CONSECUENCIA:
🔄 ¡EL PROGRAMA VUELVE ARRIBA AL DO!
⚠️ La variable i NO se mueve. Seguimos en la casilla 2..


🟢 VUELTA 3 (REINTENTO): Seguimos en la Casilla 2 (i=2)


Código ejecutándose                 Qué pasa en la Memoria
do {                                Volvemos a entrar (Reintento forzoso).
candidato = scn.nextInt();          ⌨️ El usuario escribe un 9.
Variable candidato = 9.

valido = true;                       🟢 RESET: Importante!. Se asigna aqui la variable para que si en un caso como el anterior es false se reinicie a true.

for (int j = 0; j < i; j++)           INSPECTOR (j=0): 9 vs 5 (No).  INSPECTOR (j=1): 9 vs 8 (No).
if (candidato == numeros[j])          Ninguno coincide.

while (!valido);                       "¿Es inválido? NO.
✅ Ahora sí, salimos del bucle."

numerosUsuario[i] = candidato;           💾 GUARDADO: Metemos el 9 en la posición 2.


Estado Final del Array:,"[ 5, 8, 9, 0, 0 ]"


Y asi seguiriamos hasta llenar las 5 posiciones del array




*/
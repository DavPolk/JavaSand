#Variables
JC = javac

#Rule to build the classes
default: Grid.class Particle.class GameCanvas.class Particulate.class Fluid.class Spout.class Menu.class MouseMotionInput.class Sand.class Stone.class Plant.class Fire.class Gunpowder.class Conway.class GravWell.class BlackHole.class

#Rule to build main.class
Main.class: Main.java
	$(JC) -g Main.java

Grid.class: Grid.java
	$(JC) -g Grid.java

Particle.class: Particle.java
	$(JC) -g Particle.java

GameCanvas.class: GameCanvas.java
	$(JC) -g GameCanvas.java

Particulate.class: Particulate.java
	$(JC) -g Particulate.java

Fluid.class: Fluid.java
	$(JC) -g Fluid.java

Spout.class: Spout.java
	$(JC) -g Spout.java

Menu.class: Menu.java
	$(JC) -g Menu.java

MouseMotionInput.class: MouseMotionInput.java
	$(JC) -g MouseMotionInput.java

Sand.class: Sand.java
	$(JC) -g Sand.java

Stone.class: Stone.java
	$(JC) -g Stone.java

Plant.class: Plant.java
	$(JC) -g Plant.java

Fire.class: Fire.java
	$(JC) -g Fire.java

Gunpowder.class: Gunpowder.java
	$(JC) -g Gunpowder.java

Conway.class: Conway.java
	$(JC) -g Conway.java

GravWell.class: GravWell.java
	$(JC) -g GravWell.java

BlackHole.class: BlackHole.java
	$(JC) -g BlackHole.java

# Target to run main
run: default
	java GameCanvas

# Remove class files
clean:
	rm -f *.class javaSand.jar

jar: default
	jar cfm javaSand.jar manifest.txt *.class
	java -cp javaSand.jar GameCanvas
#Variables
JC = javac

#Rule to build the classes
default: Grid.class Particle.class GameCanvas.class Particulate.class Fluid.class ParticulateSpout.class Spout.class Menu.class MouseInput.class MouseMotionInput.class Sand.class Stone.class

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

ParticulateSpout.class: ParticulateSpout.java
	$(JC) -g ParticulateSpout.java

Menu.class: Menu.java
	$(JC) -g Menu.java

MouseInput.class: MouseInput.java
	$(JC) -g MouseInput.java

MouseMotionInput.class: MouseMotionInput.java
	$(JC) -g MouseMotionInput.java

Sand.class: Sand.java
	$(JC) -g Sand.java

Stone.class: Stone.java
	$(JC) -g Stone.java

# Target to run main
run: default
	java GameCanvas

# Remove class files
clean:
	rm -f *.class
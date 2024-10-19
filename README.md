# Cursor Controller with Keyboard (Java & JNI)

This project is a simple application that allows you to control the mouse cursor using the keyboard. By pressing a combination of keys, you can move the cursor and perform basic actions like left-clicking.

## Features
- Enter "cursor mode" by pressing `Ctrl + Shift`
- Use arrow keys to move the cursor
- Press the `End` key to perform a left-click action

## How It Works
1. **Activate cursor mode**: Hold `Ctrl + Shift` to enter cursor mode.
2. **Move the cursor**: While in cursor mode, use the arrow keys (`↑`, `↓`, `←`, `→`) to move the cursor.
3. **Perform a left-click**: Press the `End` key while in cursor mode to simulate a left-click.

## Prerequisites
- Java JDK installed (version 8 or higher)
- A C++ compiler to compile the JNI (Java Native Interface) bindings
- An OS that supports Java and JNI

## Build and Run
1. Clone the repository:
    ```bash
    git clone https://github.com/Guuri11/cursor-controller-keyboard.git
    cd cursor-controller-keyboard
    ```

2. Compile the Java code:
    ```bash
    mvn clean package
    ```

3. Run the application:
    ```bash
    java -jar target/mouse-keyboard-1.0-SNAPSHOT.jar
    ```

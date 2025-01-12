# Tetris Simulation

A Java-based Tetris simulation that calculates the final stack height of blocks based on input sequences.

## Table of Contents

- [Description](#description)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Input Format](#input-format)
- [Testing](#testing)

## Description

This project simulates a simplified version of Tetris where blocks are dropped based on specified input commands. The program computes the height of the stack after processing the commands.

## Features

- Supports various Tetris shapes (`Q`, `Z`, `S`, `T`, `I`, `L`, `J`).
- Automatically calculates the resulting height after completing rows.
- Command-line input processing.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yanallama/tetris.git
   cd tetris

2. Build the project using Gradle:
   ```bash
   ./gradlew build
3. The JAR file will be located in `build/libs/`:
    ```bash  
   build/libs/tetris-1.0.jar

I also included a pre-built jar in `src/test/resources/` as a backup.

## Usage
Run the program using the following command:

```bash
    java -jar build/libs/tetris-1.0.jar
```
### Run
After running the command, you can enter sequences of commands. Press `Enter` after each input line. Terminate input with `Ctrl+D` (Linux/Mac) or `Ctrl+Z` (Windows).

### Input from File
Alternatively, provide input from a file:

```bash
  java -jar build/libs/tetris-1.0.jar < src/test/resources/input.txt
```

## Input Format
Each block is represented by a letter (Q, Z, S, T, I, L, J) followed by a column index (e.g., Q0, T4).
Multiple blocks are separated by commas (e.g., Q0,Q1,Q2).

## Testing
Run the test suite to verify functionality:
```bash
./gradlew test


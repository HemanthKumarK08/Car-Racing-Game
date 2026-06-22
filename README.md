# 🚗 Car Racing Game

A modern desktop-based **2D Car Racing Game** built using **Java**, **Java Swing**, and **AWT** that provides an engaging racing experience with smooth animations, dynamic enemy spawning, increasing difficulty levels, collision detection, score tracking, and immersive sound effects.

---

## 📌 Overview

The **Car Racing Game** is a Java desktop application designed to demonstrate real-time game development concepts using Object-Oriented Programming. The player controls a racing car, avoids incoming enemy vehicles, and survives as long as possible to achieve the highest score.

The game features an attractive user interface, responsive controls, progressive difficulty, and a modular architecture, making it an excellent demonstration of Java GUI and game development.

---

## ✨ Key Features

### 🎮 Gameplay

- Modern Racing-Themed UI
- Responsive Keyboard Controls
- Smooth Vehicle Movement
- Random Enemy Vehicle Generation
- Collision Detection
- Dynamic Difficulty Increase
- Real-Time Score Tracking
- High Score Management
- Pause & Resume
- Game Over Screen
- Restart Option
- Animated Road Movement
- Sound Effects
- Attractive Menu Screen

---

## 🎯 Game Workflow

```text
Launch Game
      │
      ▼
Main Menu
      │
      ▼
Click "Start Game"
      │
      ▼
Gameplay Begins
      │
      ▼
Move Car Left / Right
      │
      ▼
Avoid Enemy Vehicles
      │
      ▼
Score Increases
      │
      ▼
Game Speed Increases
      │
      ▼
Collision?
 ┌──────────────┐
 │     No       │
 │ Continue     │
 └──────────────┘
        │
       Yes
        │
        ▼
Game Over
        │
        ▼
Display Final Score
        │
        ▼
Restart or Return to Menu
```

---

## 🎮 Gameplay Features

### 🚘 Player Controls

- Move Left (← / A)
- Move Right (→ / D)
- Pause / Resume (P)
- Restart Game (R)
- Return to Menu (ESC)

---

### 🚗 Enemy Vehicles

- Randomly generated
- Multiple enemy car designs
- Random lane spawning
- Continuous movement
- Speed increases over time

---

### 💥 Collision Detection

The game automatically detects collisions between the player's vehicle and enemy cars.

On collision:

- Game Stops
- Crash Sound Plays
- Final Score Displays
- High Score Updates
- Restart Option Appears

---

### 📈 Difficulty System

The game automatically becomes more challenging by:

- Increasing vehicle speed
- Faster enemy movement
- Higher gameplay intensity

No manual level selection is required.

---

### 🏆 Score System

The game continuously tracks:

- Current Score
- Highest Score

High scores are automatically saved using Java File Handling and loaded whenever the game starts.

---

## 🛠 Technologies Used

| Technology | Purpose |
|------------|---------|
| Java | Core Programming |
| Java Swing | GUI Development |
| Java AWT | Graphics & Event Handling |
| Graphics2D | Rendering & Animation |
| Java Sound API | Background Music & Sound Effects |
| File Handling | High Score Storage |
| OOP | Modular Game Architecture |
| VS Code | Development Environment |
| Git | Version Control |

---

## 📁 Project Structure

```text
CarRacingGame/

├── src/
│
├── assets/
│   ├── menu_bg.png
│   ├── road.png
│   ├── player.png
│   ├── enemy1.png
│   ├── enemy2.png
│   ├── enemy3.png
│   ├── background.wav
│   └── crash.wav
│
├── main/
│   └── Main.java
│
├── ui/
│   ├── GameFrame.java
│   ├── GamePanel.java
│   ├── MenuPanel.java
│   └── GameOverPanel.java
│
├── model/
│   ├── PlayerCar.java
│   └── Obstacle.java
│
├── manager/
│   ├── CollisionManager.java
│   ├── DifficultyManager.java
│   └── ScoreManager.java
│
├── utils/
│   └── SoundManager.java
│
└── README.md
```

---

## 🎵 Sound Features

The game includes:

- Background Engine Sound
- Crash Sound Effect
- Continuous Background Music
- Automatic Sound Control

---

## 🖥 User Interface

The application includes:

- Modern Menu Screen
- Racing Background
- Animated Gameplay
- Live Score Display
- Speed Indicator
- Pause Screen
- Game Over Screen
- High Score Display

---

## 🚀 Getting Started

### Clone the Repository

```bash
git clone https://github.com/HemanthKumarK08/CarRacingGame.git
```

---

### Navigate to Project

```bash
cd CarRacingGame
```

---

### Compile the Project

```bash
javac -d out $(find src -name "*.java")
```

---

### Run the Game

```bash
java -cp out main.Main
```

Or, if using an IDE like VS Code, IntelliJ IDEA, or Eclipse, simply run:

```bash
main.Main
```

---

## 📸 Screenshots

Add screenshots of:

- Main Menu
- Gameplay Screen
- Pause Screen
- Game Over Screen

---

## 🎯 Learning Outcomes

This project demonstrates:

- Object-Oriented Programming (OOP)
- Java Swing GUI Development
- Event Handling
- Real-Time Animation
- Collision Detection
- File Handling
- Java Sound Integration
- Modular Software Design
- Game Development Fundamentals

---

## 🔮 Future Enhancements

- Multiple Difficulty Levels
- New Racing Tracks
- Multiple Player Cars
- AI-Controlled Opponents
- Power-Ups
- Online Leaderboard
- Database Integration
- Multiplayer Mode
- Mobile Version
- Better Graphics & Particle Effects

---

## 👨‍💻 Author

**Hemanth Kumar K**

MCA Student | Bangalore Institute of Technology, Bengaluru

---

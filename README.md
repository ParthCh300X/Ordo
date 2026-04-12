<div align="center">

# 🍽️ Ordo
### Smart Cafeteria Management System

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)
![MVVM](https://img.shields.io/badge/Architecture-MVVM-orange?style=for-the-badge)

> *A real-time cafeteria ordering system that models how modern
> food operations work under load.*

</div>

---

## 📌 Overview

Ordo is not a food ordering app. It's a **real-time operational system**
designed around how cafeterias actually function — queues, prep times,
live state transitions, and decision support under load.

Built with Firebase Firestore snapshot listeners, every order update
propagates instantly across the system without polling.

---

## ✨ Features

### 🧑‍🍳 User Side
- Browse dynamic menu — Meals, Snacks, Drinks
- Smart recommendations based on usage patterns
- Cart with real-time updates
- Smooth, minimal checkout flow

### 📦 Real-Time Order Lifecycle

- Firestore snapshot listeners — zero polling
- Instant UI updates on every state change
- Visual timeline with live transitions

### ⏱️ Queue & Wait-Time Prediction
- Estimated wait time calculated from:
  - Item preparation time
  - Current queue load
- Dynamically updates as queue changes

### 🧾 Order History
- Per-user order history
- Persistent via Firestore

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | MVVM + Clean Architecture |
| Backend | Firebase Auth + Firestore |
| Async | Kotlin Coroutines + Flow |
| State | StateFlow + Compose State |
| Images | Coil |

---

---

## 📱 UI/UX Decisions

- Skeleton loaders instead of blocking spinners
- Clear visual hierarchy — Image → Name → Price → Action
- Real-time feedback on every interaction
- Smooth navigation and state transitions
- Minimal, intentional design — no unnecessary elements

---

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- Firebase project with Auth + Firestore enabled

### Setup

1. Clone the repository
```bash
git clone https://github.com/ParthCh300x/Ordo.git
```

2. Open in Android Studio

3. Connect to Firebase
   - Go to Firebase Console → Add Android app
   - Download `google-services.json`
   - Place in `/app` directory
   - Enable Authentication (Email/Password)
   - Enable Firestore Database

4. Run the app

```bash
./gradlew assembleDebug
```

---

## 🔮 Roadmap

- [ ] Vendor dashboard — order management + queue control
- [ ] Admin panel — analytics + menu management
- [ ] ML-based recommendation engine
- [ ] Offline-first support with RoomDB
- [ ] Push notifications for order readiness
- [ ] Multi-campus support

---

## 👤 Author

**Parth Chaudhary**
IoT Engineering · IIIT Nagpur
[GitHub](https://github.com/ParthCh300x) · [LinkedIn](https://www.linkedin.com/in/parth-chaudhary-615124287/)

---

<div align="center">
<i>Building systems that feel like products, not projects.</i>
</div>

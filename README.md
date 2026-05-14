# 🏛️ NammaKathey – Interactive Heritage Learning App

An immersive Android application designed to help children and young learners explore the inspiring stories of Karnataka’s local heroes through storytelling, quizzes, achievements, and gamified learning.

Developed as part of the **VTU Internship Program (2022 Scheme)** under **MINDMATRIX** for **Android App Development using Generative AI**.

---

## 📌 Project Overview

NammaKathey transforms traditional historical learning into an interactive mobile experience.

The application introduces users to legendary personalities from Karnataka through:

- Story-based learning
- District-wise hero exploration
- Interactive quizzes
- Badge unlocking system
- Personalized progress tracking
- Bilingual support (English + Kannada)

Hero and district content is managed through structured local JSON datasets. :contentReference[oaicite:0]{index=0}

---

## 🎯 Problem Statement

Children often find historical learning text-heavy and unengaging.

NammaKathey addresses this by creating a:

- Gamified learning environment
- Visual storytelling platform
- Region-specific cultural education system

that makes heritage education fun, memorable, and interactive.

---

## ✨ Key Features

### 1. Interactive Hero Stories
Explore inspiring stories of Karnataka heroes including:

- Kittur Rani Chennamma
- Kempe Gowda
- Tipu Sultan
- Kuvempu
- Sangolli Rayanna
- Allama Prabhu
- Basavanna
- Many more...

Hero data is dynamically loaded from local structured content files. 

---

### 2. District-Based Exploration

Users can explore heroes district-wise across Karnataka including:

- Bengaluru
- Belagavi
- Mysuru
- Shivamogga
- Dharwad
- Kodagu
- Udupi
- Ballari
- and others

District metadata is maintained through local JSON resources. :contentReference[oaicite:2]{index=2}

---

### 3. Smart Quiz System

Each hero includes a quiz module featuring:

✅ Multiple choice questions  
✅ Score tracking  
✅ Time tracking  
✅ Instant answer feedback  
✅ Result analytics  

Quiz implementation handled in QuizActivity. :contentReference[oaicite:3]{index=3}

---

### 4. Achievement & Badge System

Users unlock badges by successfully completing hero quizzes.

Badge features include:

- Hero-specific badges
- Earned/Locked states
- Achievement animations
- Progress visualization

Badge reward navigation integrated with quiz completion flow. :contentReference[oaicite:4]{index=4}

---

### 5. User Authentication & Profile

Includes:

- Name registration
- Avatar selection
- Age group selection
- Personalized profile dashboard

Authentication and onboarding logic implemented using MVVM architecture. :contentReference[oaicite:5]{index=5}

---

### 6. Parent Dashboard

Parents can monitor:

- Stories completed
- Quiz progress
- Badges earned
- Learning engagement

Dashboard UI implemented through dedicated layouts. :contentReference[oaicite:6]{index=6}

---

## 🏗️ Architecture

This project follows **MVVM Architecture** with clean separation of concerns.

```text
UI Layer
↓
ViewModel Layer
↓
Repository Layer
↓
Local Data Layer
```

Repository implementation handles app data management. :contentReference[oaicite:7]{index=7}

---

## 🛠️ Tech Stack

### Language
- Kotlin

### Architecture
- MVVM

### Android Components
- RecyclerView
- Navigation Component
- View Binding
- LiveData / Flow

### Dependency Injection
- Hilt

### Local Storage
- Room Database
- DataStore Preferences

Preferences and local storage implemented using DataStore. :contentReference[oaicite:8]{index=8}

### UI/UX
- Material Design Components
- Custom Animations
- Lottie Animations

---

## 📱 Screens Included

- Splash Screen
- Onboarding
- Authentication
- Home Dashboard
- District Explorer
- Hero Story Reader
- Quiz Screen
- Badge Screen
- Profile Screen
- Parent Dashboard

---

## 🌐 Localization Support

Supports bilingual learning:

- English
- ಕನ್ನಡ (Kannada)

Designed specifically for regional educational accessibility.

---

## 🚀 Future Enhancements

Planned improvements:

- Cloud sync using Firebase
- AI-generated story narration
- Voice interaction
- Multiplayer quiz mode
- Leaderboard system
- Offline downloadable story packs

---

## 🎓 Internship Details

**Internship Title:** Android App Development using Generative AI  
**University:** Visvesvaraya Technological University (VTU)  
**Academic Scheme:** 2022 Scheme  
**Organization:** MINDMATRIX  

---

## 👨‍💻 Developer

**Akshaya K**  
VTU Internship Project Submission

---

## 📷 App Preview

(Add screenshots here)

---

## ⭐ Conclusion

NammaKathey combines technology, storytelling, and gamification to preserve Karnataka’s cultural heritage while making learning enjoyable for children.

This project demonstrates Android development, architectural design, UI/UX implementation, data persistence, and gamification concepts in a real-world educational application.

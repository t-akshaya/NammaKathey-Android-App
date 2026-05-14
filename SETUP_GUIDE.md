# 🏛️ Namma Kathey (ನಮ್ಮ ಕಥೆ) — Android Studio Setup Guide

## 📦 Project Architecture

```
NammaKathey/
├── app/src/main/
│   ├── java/com/nammakathe/
│   │   ├── NammaKatheyApp.kt              ← Hilt Application class
│   │   ├── core/
│   │   │   ├── data/
│   │   │   │   ├── local/
│   │   │   │   │   ├── dao/UserProgressDao.kt
│   │   │   │   │   ├── database/
│   │   │   │   │   │   ├── NammaKatheyDatabase.kt
│   │   │   │   │   │   └── PreferencesManager.kt
│   │   │   │   │   └── entity/Entities.kt
│   │   │   │   └── repository/
│   │   │   │       ├── HeroDataSource.kt
│   │   │   │       ├── NammaKatheyRepository.kt
│   │   │   │       └── AiStoryService.kt
│   │   │   ├── domain/
│   │   │   │   └── model/
│   │   │   │       ├── Hero.kt
│   │   │   │       └── UserProgress.kt
│   │   │   └── util/
│   │   │       ├── Extensions.kt
│   │   │       ├── Constants.kt
│   │   │       └── DailyReminderWorker.kt
│   │   ├── di/
│   │   │   └── AppModule.kt
│   │   └── ui/
│   │       ├── splash/SplashActivity.kt + ViewModel
│   │       ├── onboarding/OnboardingActivity.kt + Adapter + ViewModel
│   │       ├── auth/AuthActivity.kt + ViewModel
│   │       ├── home/
│   │       │   ├── MainActivity.kt
│   │       │   ├── MainViewModel.kt
│   │       │   └── HomeFragment.kt
│   │       ├── district/
│   │       │   ├── DistrictFragment.kt
│   │       │   ├── HeroListFragment.kt
│   │       │   └── StatueFinderFragment.kt
│   │       ├── story/
│   │       │   ├── StoryActivity.kt + ViewModel
│   │       │   └── AiStoryFragment.kt
│   │       ├── quiz/QuizActivity.kt + ViewModel
│   │       ├── badge/
│   │       │   ├── BadgeFragment.kt
│   │       │   ├── BadgeViewModel.kt
│   │       │   └── BadgeEarnedActivity.kt
│   │       ├── profile/
│   │       │   ├── ProfileFragment.kt
│   │       │   └── ParentDashboardFragment.kt
│   │       └── adapter/
│   │           ├── HeroCardAdapter.kt
│   │           ├── DistrictAdapter.kt
│   │           ├── StoryPagerAdapter.kt
│   │           └── BadgeAdapter.kt
│   ├── res/
│   │   ├── layout/          ← 20+ XML layouts
│   │   ├── drawable/        ← 20+ gradient & shape drawables
│   │   ├── values/          ← colors, strings, themes, dimens
│   │   ├── anim/            ← 8 animations
│   │   ├── navigation/      ← nav_graph.xml
│   │   ├── menu/            ← bottom_nav_menu.xml
│   │   └── raw/             ← 5 Lottie JSON files
│   ├── assets/
│   │   └── heroes_data.json ← 5 heroes, 5 districts, 5 badges
│   └── AndroidManifest.xml
├── app/build.gradle
├── build.gradle
├── settings.gradle
└── gradle.properties
```

---

## 🚀 Step-by-Step Android Studio Setup

### Step 1 — Open Project
1. Launch **Android Studio Hedgehog (2023.1.1)** or newer
2. Click **File → Open** → select the `NammaKatheyPro/` folder
3. Wait for Gradle to sync (may take 3–5 minutes first time)

### Step 2 — Set SDK Path
Edit `local.properties`:
```properties
sdk.dir=/Users/YourName/Library/Android/sdk       # macOS
sdk.dir=C:\\Users\\YourName\\AppData\\Local\\Android\\Sdk  # Windows
sdk.dir=/home/yourname/Android/Sdk                # Linux
```

### Step 3 — API Keys Setup

#### Google Maps API Key
1. Go to https://console.cloud.google.com
2. Create project → Enable **Maps SDK for Android**
3. Create API Key → copy it
4. In `AndroidManifest.xml`, replace:
```xml
android:value="YOUR_GOOGLE_MAPS_API_KEY"
```

#### Gemini AI API Key (for AI Story Mode)
1. Go to https://makersuite.google.com/app/apikey
2. Create API Key → copy it
3. In `AiStoryService.kt`, replace:
```kotlin
apiKey = "YOUR_GEMINI_API_KEY"
```
Or better, add to `local.properties`:
```properties
GEMINI_API_KEY=your_actual_key_here
```

### Step 4 — Lottie Animations
Download free animations from https://lottiefiles.com and replace these files in `res/raw/`:
- `splash_animation.json` — Karnataka map / star burst
- `correct_animation.json` — Celebration / checkmark
- `wrong_animation.json` — Shake / X mark  
- `celebration.json` — Confetti / trophy
- `try_again.json` — Book / retry arrow

The app ships with minimal placeholder Lottie files that work but look basic.

### Step 5 — Build & Run
```bash
# Or simply press the ▶ Run button in Android Studio
./gradlew assembleDebug
```

---

## ✅ Features Implemented

| Feature | Status |
|---------|--------|
| Animated Splash Screen | ✅ |
| 3-Page Onboarding | ✅ |
| Profile Setup (name, age group, avatar) | ✅ |
| Guest Mode | ✅ |
| Home Dashboard with Daily Hero | ✅ |
| Hero Search | ✅ |
| District Grid (7 districts) | ✅ |
| Hero List per District | ✅ |
| Swipe Storybook (ViewPager2) | ✅ |
| Text-to-Speech (EN + Kannada) | ✅ |
| EN / Kannada Language Toggle | ✅ |
| Timed Quiz with 4 options | ✅ |
| Correct/Wrong Animations | ✅ |
| Heritage Badge System | ✅ |
| Badge Gallery | ✅ |
| Badge Earned Screen with Share | ✅ |
| Google Maps Statue Finder | ✅ |
| AI Story Mode (Gemini) | ✅ |
| Room DB Progress Tracking | ✅ |
| DataStore Preferences | ✅ |
| Favorite Heroes | ✅ |
| Parent Dashboard | ✅ |
| Dark Mode Toggle | ✅ |
| Daily Reminder Notification | ✅ |
| MVVM + Hilt + Clean Architecture | ✅ |
| 5 Heroes with full stories & quizzes | ✅ |
| Proguard rules | ✅ |

---

## 📱 App Flow

```
Splash → Onboarding (3 pages) → Auth (Name + Age + Avatar)
    ↓
Home (Daily Hero + Stats + Search + All Heroes)
    ↓
Tap District → Hero List → Story (ViewPager2 + TTS)
    ↓
Quiz (Timer + Options + Animations) → Badge Earned
    ↓
Profile → Parent Dashboard / Settings
```

---

## 🗂️ Adding More Heroes

Edit `app/src/main/assets/heroes_data.json`:
```json
{
  "id": "hero_unique_id",
  "name": "Hero Name",
  "nameKn": "ವೀರ ಹೆಸರು",
  "districtId": "mysuru",
  ...
  "pages": [ { "pageNumber": 1, "title": "...", ... } ],
  "quiz": [ { "id": "q1", "question": "...", "correctIndex": 0 } ],
  "badgeId": "badge_hero_id"
}
```

---

## 🏗️ Tech Stack

- **Kotlin** — Primary language
- **MVVM + Clean Architecture** — Separation of concerns
- **Hilt** — Dependency Injection
- **Room** — Local database for progress
- **DataStore** — User preferences
- **ViewPager2** — Swipeable storybook
- **Navigation Component** — Fragment navigation
- **Coroutines + Flow** — Async operations
- **Lottie** — Animations
- **Glide** — Image loading
- **Google Maps SDK** — Statue/memorial finder
- **Gemini AI** — Age-adapted story generation
- **WorkManager** — Daily reminder notifications
- **TextToSpeech** — Audio narration
- **Material Design 3** — UI components

---

## 📞 Support
Built with ❤️ for Karnataka's young learners.
Namma Naadu, Namma Kathey — ನಮ್ಮ ನಾಡು, ನಮ್ಮ ಕಥೆ

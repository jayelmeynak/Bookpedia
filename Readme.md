# Books 📚

[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.20-blue.svg)](https://kotlinlang.org)
[![minSdk](https://img.shields.io/badge/minSdk-24-green)](https://developer.android.com)

**Books** — мультиплатформенное приложение для поиска, просмотра деталей и управления избранными книгами. Приложение работает на Android, iOS и Desktop, используя современную архитектуру MVI, Ktor для работы с сетью, Koin для DI и Room для локального хранения.


## Основные функции 🌟
- 🔍 **Поиск книг:** ввод запроса и вывод списка книг с базовой информацией.
- 📖 **Детали книги:** подробный просмотр информации о выбранной книге.
- ❤️ **Избранное:** добавление книг в список избранного с сохранением в локальной базе данных (Room).
- 🌐 **Мультиплатформенность:** единый код для Android, iOS и Desktop.

## Технологии 🛠️
- **Язык:** Kotlin
- **UI:** Jetpack Compose / Compose Multiplatform
- **Архитектура:** MVI (Model-View-Intent)
- **Сеть:** Ktor
- **DI:** Koin
- **Локальное хранение:** Room
- **Асинхронность:** Coroutines + Flow

## API 📡
Приложение использует публичное API для поиска книг ([Open Library API](https://openlibrary.org/)) для получения данных.

## Установка ⚙️
1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/your-username/BookVerse.git
   cd BookVerse
   ```
2. Откройте проект в Android Studio или IntelliJ IDEA.
3. Убедитесь, что установлен JDK 17 (или используйте Gradle Toolchains).
4. Запустите приложение:

    Android: запустите на эмуляторе или реальном устройстве через Android Studio.
    
    Desktop: выполните:
    ```bash
    ./gradlew run
    ```
    iOS: настройте таргет для iOS согласно документации Kotlin Multiplatform и запустите через Xcode.



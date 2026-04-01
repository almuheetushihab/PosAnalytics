# PosAnalytics - POS Sales Analytics Dashboard

## 📌 Project Overview
**PosAnalytics** is a modern Android application designed to provide business owners and managers with a real-time, intuitive overview of their Point of Sale (POS) performance. It transforms raw transaction data into actionable insights through a clean, interactive dashboard.

## 🎯 Why was this project made? (The Purpose)
Managing a business requires quick access to financial data. This project was built to:
1.  **Simplify Financial Monitoring:** Instead of looking at complex spreadsheets, users can see their Gross Sales, Refunds, and Net Sales at a glance.
2.  **Facilitate Data-Driven Decisions:** By providing time-based filters, the app helps identify sales trends (Daily, Weekly, or All-Time).
3.  **Modernize POS Interfaces:** Demonstrates how Jetpack Compose can be used to build high-performance, reactive business tools.

## 🚀 Key Features
-   **KPI Cards:** High-level summary of Gross Sales, Total Refunds, and Net Profit.
-   **Dynamic Time Filters:** Quickly toggle between *Today*, *Yesterday*, *This Week*, and *All-Time* views.
-   **Transaction History:** A detailed list of recent invoices, showing the transaction type (Sale vs. Refund), date, and amount.
-   **Visual Cues:** Uses color-coded icons (Green for Sales, Red for Refunds) for instant recognition of data types.
-   **Responsive UI:** Built entirely with Jetpack Compose for a smooth and modern user experience.

## 🛠️ How it Works (Technical Documentation)

### Architecture
The project follows the **MVVM (Model-View-ViewModel)** architectural pattern:
-   **Model:** Defines the data structures for `SaleRecord`, `TransactionType`, and `TimeFilter`.
-   **ViewModel (`DashboardViewModel`):** The engine of the app. It holds the "source of truth" (mock sales data) and contains the logic for filtering and calculating totals (Gross, Refund, Net) based on the user's selected time range.
-   **View (`DashboardScreen`):** A stateless UI layer that observes the ViewModel and renders the data using Composable functions.

### Tech Stack
-   **Language:** Kotlin
-   **UI Framework:** Jetpack Compose (Material 3)
-   **State Management:** `mutableStateOf` and `derivedStateOf` for efficient UI updates.
-   **Date Handling:** Java 8 Time API (`java.time`) for precise transaction filtering.

## 📂 Project Structure
```text
com.shihab.posanalytics
├── models/             # Data classes (SaleRecord, Enums)
├── viewmodel/          # Business logic and state handling
└── ui/
    └── screens/        # Dashboard UI and Composable components
```

## 🏗️ Getting Started
1. Clone the repository.
2. Open the project in **Android Studio**.
3. Sync the project with Gradle files.
4. Run the app on an emulator or physical device (API 26+ required).

---
*Developed by Shihab*

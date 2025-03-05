package com.example.kursovikcmp.DB

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.example.kursovikcmp.Database

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver = NativeSqliteDriver(Database.Schema.synchronous(), "database.db")
}
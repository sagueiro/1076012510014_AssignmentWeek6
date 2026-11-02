package com.vp.a1076012510014_assignmentweek6.data

import com.vp.a1076012510014_assignmentweek6.model.Community
import com.vp.a1076012510014_assignmentweek6.model.Project

object CampusRepository {

    val techOptions = listOf(
        "Kotlin", "Jetpack Compose", "Firebase", "Ktor", "Room", "Python", "Maps SDK"
    )

    fun getCommunities(): List<Community> {
        return listOf(
            Community(
                "gdsc", "GDSC", "Google Developer Student Club",
                "Fokus pada ekosistem Google: Android Compose, Flutter, Firebase, dan Google Cloud Platform.",
                142, 0xFF4285F4, true
            ),
            Community(
                "cyber", "CyberSecurity Guild", "Security & Pentesting",
                "Eksplorasi ethical hacking, CTF challenges, reverse engineering, dan pertahanan jaringan kampus.",
                88, 0xFFE53935, true
            ),
            Community(
                "ai", "AI Society", "Machine Learning & LLM",
                "Riset terapan deep learning, computer vision, agen cerdas, dan fine-tuning large language models.",
                115, 0xFF7E57C2, false
            ),
            Community(
                "mobile", "Mobile Dev Community", "Jetpack Compose & KMP",
                "Belajar membuat aplikasi mobile native dengan Jetpack Compose dan Kotlin Multiplatform.",
                95, 0xFF0288D1, true
            ),
            Community(
                "web", "Web Dev Club", "Fullstack & Ktor",
                "Membahas pengembangan web modern, REST API, dan backend dengan Ktor.",
                76, 0xFF43A047, false
            ),
            Community(
                "data", "Data Science Club", "Python & Analytics",
                "Belajar analisis data, visualisasi, dan machine learning dasar dengan Python.",
                64, 0xFFFB8C00, false
            )
        )
    }

    fun getProjects(): List<Project> {
        return listOf(
            Project(
                "proj-gdsc-01", "gdsc", "Smart Campus Navigation",
                "Sistem navigasi peta gedung kampus interaktif berbasis Jetpack Compose dan Google Maps SDK.",
                listOf("Kotlin", "Jetpack Compose", "Room", "Maps SDK"), false
            ),
            Project(
                "proj-gdsc-02", "gdsc", "Student Attendance QR Scanner",
                "Pemindai barcode dan QR untuk presensi kehadiran workshop berbasis Firebase.",
                listOf("Jetpack Compose", "Firebase"), true
            ),
            Project(
                "proj-cyber-01", "cyber", "Network Scanner Lab",
                "Tools sederhana untuk memindai port pada jaringan lab kampus.",
                listOf("Python"), false
            ),
            Project(
                "proj-mobile-01", "mobile", "Campus Event App",
                "Aplikasi daftar event kampus dengan fitur pengingat.",
                listOf("Kotlin", "Jetpack Compose"), false
            )
        )
    }
}
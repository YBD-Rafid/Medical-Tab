package com.example.medical_tab.api

import com.example.medical_tab.model.SectionLineModel
import kotlinx.coroutines.delay

//class MockApiService : ApiService {
//    override suspend fun getSectionLines(): List<SectionLineModel> {
//        delay(500) // Simulate network delay
//        return listOf(
//            SectionLineModel("A-01", "Line 01", "A", "Section A"),
//            SectionLineModel("A-02", "Line 02", "A", "Section A"),
//            SectionLineModel("A-03", "Line 03", "A", "Section A"),
//            SectionLineModel("A-04", "Line 04", "A", "Section A"),
//            SectionLineModel("A-05", "Line 05", "A", "Section A"),
//            SectionLineModel("A-06", "Line 06", "A", "Section A"),
//            SectionLineModel("A-07", "Line 07", "A", "Section A"),
//            SectionLineModel("A-08", "Line 08", "A", "Section A"),
//            SectionLineModel("A-09", "Line 09", "A", "Section A"),
//            SectionLineModel("A-10", "Line 10", "A", "Section A"),
//
//            // Section B - 10 Lines
//            SectionLineModel("B-01", "Line 01", "B", "Section B"),
//            SectionLineModel("B-02", "Line 02", "B", "Section B"),
//            SectionLineModel("B-03", "Line 03", "B", "Section B"),
//            SectionLineModel("B-04", "Line 04", "B", "Section B"),
//            SectionLineModel("B-05", "Line 05", "B", "Section B"),
//            SectionLineModel("B-06", "Line 06", "B", "Section B"),
//            SectionLineModel("B-07", "Line 07", "B", "Section B"),
//            SectionLineModel("B-08", "Line 08", "B", "Section B"),
//            SectionLineModel("B-09", "Line 09", "B", "Section B"),
//            SectionLineModel("B-10", "Line 10", "B", "Section B"),
//
//            // Section C - 10 Lines
//            SectionLineModel("C-01", "Line 01", "C", "Section C"),
//            SectionLineModel("C-02", "Line 02", "C", "Section C"),
//            SectionLineModel("C-03", "Line 03", "C", "Section C"),
//            SectionLineModel("C-04", "Line 04", "C", "Section C"),
//            SectionLineModel("C-05", "Line 05", "C", "Section C"),
//            SectionLineModel("C-06", "Line 06", "C", "Section C"),
//            SectionLineModel("C-07", "Line 07", "C", "Section C"),
//            SectionLineModel("C-08", "Line 08", "C", "Section C"),
//            SectionLineModel("C-09", "Line 09", "C", "Section C"),
//            SectionLineModel("C-10", "Line 10", "C", "Section C"),
//
//            // Section D - 10 Lines
//            SectionLineModel("D-01", "Line 01", "D", "Section D"),
//            SectionLineModel("D-02", "Line 02", "D", "Section D"),
//            SectionLineModel("D-03", "Line 03", "D", "Section D"),
//            SectionLineModel("D-04", "Line 04", "D", "Section D"),
//            SectionLineModel("D-05", "Line 05", "D", "Section D"),
//            SectionLineModel("D-06", "Line 06", "D", "Section D"),
//            SectionLineModel("D-07", "Line 07", "D", "Section D"),
//            SectionLineModel("D-08", "Line 08", "D", "Section D"),
//            SectionLineModel("D-09", "Line 09", "D", "Section D"),
//            SectionLineModel("D-10", "Line 10", "D", "Section D"),
//
//            // Section E - 10 Lines
//            SectionLineModel("E-01", "Line 01", "E", "Section E"),
//            SectionLineModel("E-02", "Line 02", "E", "Section E"),
//            SectionLineModel("E-03", "Line 03", "E", "Section E"),
//            SectionLineModel("E-04", "Line 04", "E", "Section E"),
//            SectionLineModel("E-05", "Line 05", "E", "Section E"),
//            SectionLineModel("E-06", "Line 06", "E", "Section E"),
//            SectionLineModel("E-07", "Line 07", "E", "Section E"),
//            SectionLineModel("E-08", "Line 08", "E", "Section E"),
//            SectionLineModel("E-09", "Line 09", "E", "Section E"),
//            SectionLineModel("E-10", "Line 10", "E", "Section E"),
//
//            // Section F - 10 Lines
//            SectionLineModel("F-01", "Line 01", "F", "Section F"),
//            SectionLineModel("F-02", "Line 02", "F", "Section F"),
//            SectionLineModel("F-03", "Line 03", "F", "Section F"),
//            SectionLineModel("F-04", "Line 04", "F", "Section F"),
//            SectionLineModel("F-05", "Line 05", "F", "Section F"),
//            SectionLineModel("F-06", "Line 06", "F", "Section F"),
//            SectionLineModel("F-07", "Line 07", "F", "Section F"),
//            SectionLineModel("F-08", "Line 08", "F", "Section F"),
//            SectionLineModel("F-09", "Line 09", "F", "Section F"),
//            SectionLineModel("F-10", "Line 10", "F", "Section F"),
//
//            // Section G - 10 Lines
//            SectionLineModel("G-01", "Line 01", "G", "Section G"),
//            SectionLineModel("G-02", "Line 02", "G", "Section G"),
//            SectionLineModel("G-03", "Line 03", "G", "Section G"),
//            SectionLineModel("G-04", "Line 04", "G", "Section G"),
//            SectionLineModel("G-05", "Line 05", "G", "Section G"),
//            SectionLineModel("G-06", "Line 06", "G", "Section G"),
//            SectionLineModel("G-07", "Line 07", "G", "Section G"),
//            SectionLineModel("G-08", "Line 08", "G", "Section G"),
//            SectionLineModel("G-09", "Line 09", "G", "Section G"),
//            SectionLineModel("G-10", "Line 10", "G", "Section G"),
//
//            // Section H - 10 Lines
//            SectionLineModel("H-01", "Line 01", "H", "Section H"),
//            SectionLineModel("H-02", "Line 02", "H", "Section H"),
//            SectionLineModel("H-03", "Line 03", "H", "Section H"),
//            SectionLineModel("H-04", "Line 04", "H", "Section H"),
//            SectionLineModel("H-05", "Line 05", "H", "Section H"),
//            SectionLineModel("H-06", "Line 06", "H", "Section H"),
//            SectionLineModel("H-07", "Line 07", "H", "Section H"),
//            SectionLineModel("H-08", "Line 08", "H", "Section H"),
//            SectionLineModel("H-09", "Line 09", "H", "Section H"),
//            SectionLineModel("H-10", "Line 10", "H", "Section H"),
//
//            // Section I - 10 Lines
//            SectionLineModel("I-01", "Line 01", "I", "Section I"),
//            SectionLineModel("I-02", "Line 02", "I", "Section I"),
//            SectionLineModel("I-03", "Line 03", "I", "Section I"),
//            SectionLineModel("I-04", "Line 04", "I", "Section I"),
//            SectionLineModel("I-05", "Line 05", "I", "Section I"),
//            SectionLineModel("I-06", "Line 06", "I", "Section I"),
//            SectionLineModel("I-07", "Line 07", "I", "Section I"),
//            SectionLineModel("I-08", "Line 08", "I", "Section I"),
//            SectionLineModel("I-09", "Line 09", "I", "Section I"),
//            SectionLineModel("I-10", "Line 10", "I", "Section I"),
//
//            // Section J - 10 Lines
//            SectionLineModel("J-01", "Line 01", "J", "Section J"),
//            SectionLineModel("J-02", "Line 02", "J", "Section J"),
//            SectionLineModel("J-03", "Line 03", "J", "Section J"),
//            SectionLineModel("J-04", "Line 04", "J", "Section J"),
//            SectionLineModel("J-05", "Line 05", "J", "Section J"),
//            SectionLineModel("J-06", "Line 06", "J", "Section J"),
//            SectionLineModel("J-07", "Line 07", "J", "Section J"),
//            SectionLineModel("J-08", "Line 08", "J", "Section J"),
//            SectionLineModel("J-09", "Line 09", "J", "Section J"),
//            SectionLineModel("J-10", "Line 10", "J", "Section J"),
//
//            // Section K - 10 Lines
//            SectionLineModel("K-01", "Line 01", "K", "Section K"),
//            SectionLineModel("K-02", "Line 02", "K", "Section K"),
//            SectionLineModel("K-03", "Line 03", "K", "Section K"),
//            SectionLineModel("K-04", "Line 04", "K", "Section K"),
//            SectionLineModel("K-05", "Line 05", "K", "Section K"),
//            SectionLineModel("K-06", "Line 06", "K", "Section K"),
//            SectionLineModel("K-07", "Line 07", "K", "Section K"),
//            SectionLineModel("K-08", "Line 08", "K", "Section K"),
//            SectionLineModel("K-09", "Line 09", "K", "Section K"),
//            SectionLineModel("K-10", "Line 10", "K", "Section K")
//        )
//    }
//
//    override suspend fun submitSelection(idCard: String, lineId: String): Boolean {
//        delay(500)
//        return true
//    }
//}

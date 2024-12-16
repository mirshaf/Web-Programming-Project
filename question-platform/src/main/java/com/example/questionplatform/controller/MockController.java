package com.example.questionplatform.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class MockController {
    @GetMapping("/api/categories/my")
    public String m1() {
        return "{\n" +
                "  \"categories\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"تاریخ\",\n" +
                "      \"description\": \"دسته\u200Cبندی تاریخ\",\n" +
                "      \"question_count\": 50\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"علوم\",\n" +
                "      \"description\": \"دسته\u200Cبندی علوم\",\n" +
                "      \"question_count\": 75\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";
    }

    @GetMapping("/api/categories")
    public String m2() {
        return "{\n" +
                "  \"categories\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"تاریخ\",\n" +
                "      \"description\": \"دسته\u200Cبندی تاریخ\",\n" +
                "      \"question_count\": 50\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"علوم\",\n" +
                "      \"description\": \"دسته\u200Cبندی علوم\",\n" +
                "      \"question_count\": 75\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";
    }

    @GetMapping("/api/categories/{id}")
    public String m3() {
        return " {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"تاریخ\",\n" +
                "      \"description\": \"دسته\u200Cبندی تاریخ\" \n" +
                " }\n";
    }

    @PostMapping("/api/categories")
    public String m4() {
        return "{\n" +
                "  \"message\": \"Category created successfully\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 3,\n" +
                "    \"name\": \"علوم\",\n" +
                "    \"description\": \"دسته\u200Cبندی جدید برای علوم\"\n" +
                "  }\n" +
                "}\n";
    }

    @PutMapping("/api/categories/{id}")
    public String m5() {
        return "{\n" +
                "  \"message\": \"Category updated successfully\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"تاریخ\",\n" +
                "    \"description\": \"ویرایش دسته\u200Cبندی تاریخ\"\n" +
                "  }\n" +
                "}\n";
    }

    @DeleteMapping("/api/categories/{id}")
    public String m6() {
        return "{\n" +
                "  \"message\": \"Category deleted successfully\"\n" +
                "}\n";
    }

    @GetMapping("/api/questions/my")
    public String m7() {
        return "{\n" +
                "  \"questions\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"text\": \"پایتخت ایران کدام شهر است؟\",\n" +
                "      \"category\": \"جغرافیا\",\n" +
                "      \"difficulty\": \"easy\",\n" +
                "      \"created_at\": \"2024-12-06T10:00:00Z\",\n" +
                "      \"updated_at\": \"2024-12-06T12:00:00Z\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"text\": \"نویسنده کتاب 'جنگ و صلح' کیست؟\",\n" +
                "      \"category\": \"ادبیات\",\n" +
                "      \"difficulty\": \"medium\",\n" +
                "      \"created_at\": \"2024-12-05T15:30:00Z\",\n" +
                "      \"updated_at\": \"2024-12-05T16:30:00Z\"\n" +
                "    }\n" +
                "  ]\n" +
                "}\n";
    }

    @PostMapping("/api/questions")
    public String m8() {
        return "{\n" +
                "  \"message\": \"Question created successfully\",\n" +
                "  \"question\": {\n" +
                "    \"id\": 5,\n" +
                "    \"text\": \"پایتخت ایران کدام شهر است؟\"\n" +
                "  }\n" +
                "}\n";
    }

    @PutMapping("/api/questions/{id}")
    public String m9() {
        return "{\n" +
                "  \"message\": \"Question updated successfully\",\n" +
                "  \"question\": {\n" +
                "    \"id\": 1,\n" +
                "    \"text\": \"پایتخت ایران کدام شهر است؟\"\n" +
                "  }\n" +
                "}\n";
    }

    @DeleteMapping("/api/questions/{id}")
    public String m10() {
        return "{\n" +
                "  \"message\": \"Question deleted successfully\"\n" +
                "}";
    }
}

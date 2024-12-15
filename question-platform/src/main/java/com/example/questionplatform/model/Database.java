package com.example.questionplatform.model;

import com.example.questionplatform.util.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Database {
    private final Map<Integer, User> users = new HashMap<>();
    private final Map<String, User> loggedInUsers = new HashMap<>();
    private final Map<Integer, Category> categories = new HashMap<>();
    private final Map<Integer, Question> questions = new HashMap<>();
    private final Map<Integer, Answer> answers = new HashMap<>();

    public Database() {
        Question question1 = new Question("انقلاب کبیر ...؟", "انگلیس", "فرانسه", "اسپانیا", "چین", 2, Question.Difficulty_Level.easy, 1, 1);
        Question question2 = new Question("کتاب The Art of War اثر کیست؟", "Johnny Depp", "Johnny Shallow", "Johnny Deep", "Sun Tzu", 4, Question.Difficulty_Level.hard, 2, 1);
        List<Question> questions1 = new ArrayList<>();
        questions1.add(question1);
        questions1.add(question2);
        Category category1 = new Category("تاریخ", "سوالات تاریخی جالب", 1, questions1);
        Question question3 = new Question("اینجا کجاس؟", "زمین", "آسیا", "خونه", "دره", 1, Question.Difficulty_Level.medium, 1, 2);
        List<Question> questions2 = new ArrayList<>();
        questions2.add(question3);
        Category category2 = new Category("جغرافیا", "جغرافیا باحاله (الکی)", 2, questions2);

        this.questions.put(question1.getId(), question1);
        this.questions.put(question2.getId(), question2);
        this.questions.put(question3.getId(), question3);
        this.categories.put(category1.getId(), category1);
        this.categories.put(category2.getId(), category2);
        System.out.println("Database initialized...");
    }

    public boolean addAnswer(Answer answer) {
        if (this.answers.containsKey(answer.getId())) {
            return false;
        }

        this.answers.put(answer.getId(), answer);
        return true;
    }

    public Answer getAnswerById(Integer id) {
        return this.answers.get(id);
    }

    public User getUserByEmail(String email) {
        for(User user : users.values()){
            if (user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }

    public User registerUser(String username, String email, String password, String avatar_url, String role) {
        User user = new User(username, password, role, email, avatar_url);
        users.put(user.getId(), user);
        return user;
    }

    public String loginUser(User user) {
        String jwtToken = JwtUtil.generateToken(user.getEmail());
        loggedInUsers.put(jwtToken, user);
        return jwtToken;
    }

    public User getUser(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        String jwtToken = authHeader.substring(7); // Remove "Bearer " prefix
        return loggedInUsers.get(jwtToken);
    }

    public boolean logoutUser(String jwtToken) {
        return loggedInUsers.remove(jwtToken) != null;
    }

    public List<User> getUsers(String username) {
        List<User> filteredUsers = new ArrayList<>();
        for (User u :
                users.values()) {
            if (username == null || u.getUsername().equals(username)) {
                filteredUsers.add(u);
            }
        }
        return filteredUsers;
    }

    public User getUserById(Integer id) {
        return users.get(id);
    }

    public Category getCategoryByName(String name) {
        for (Category category : this.categories.values()) {
            if (category.getName().equals(name))
                return category;
        }
        return null;
    }

    public Category getCategoryById(Integer id) {
        return this.categories.get(id);
    }

    public List<Question> getQuestions(String categoryName, String difficulty) {
        Category category = (categoryName == null) ? null : getCategoryByName(categoryName);
        List<Question> filtered_questions = new ArrayList<>();
        for (Question q : this.questions.values()) {
            if (category == null || q.getCategory_id().equals(category.getId())) {
                if (difficulty == null || q.getDifficulty_level().toString().equals(difficulty)) {
                    filtered_questions.add(q);
                }
            }
        }

        return filtered_questions;
    }

    public Question getQuestionById(Integer id) {
        return questions.get(id);
    }
}

package com.example.questionplatform.model;

import com.example.questionplatform.repository.CategoryRepository;
import com.example.questionplatform.repository.QuestionRepository;
import com.example.questionplatform.repository.UserRepository;
import com.example.questionplatform.dto.response.CategoryDTO2;
import com.example.questionplatform.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Database {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private QuestionRepository questionRepository;
    private final Map<String, User> loggedInUsers = new HashMap<>();
    private final Map<Integer, Answer> answers = new HashMap<>();

    public Database() {

    }

    // User

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User registerUser(String username, String email, String password, String avatar_url, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setEmail(email);
        user.setAvatar_url(avatar_url);
        return userRepository.save(user);
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
        if (username == null) {
            return userRepository.findAll();
        }
        return userRepository.findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .toList();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // Question

    public List<Question> getQuestions(String categoryName, String difficulty) {
        Category category = (categoryName == null) ? null : getCategoryByName(categoryName);
        List<Question> filtered_questions = new ArrayList<>();
        for (Question q : questionRepository.findAll()) {
            if (category == null || q.getCategory_id().equals(category.getId())) {
                if (difficulty == null || q.getDifficulty_level().toString().equals(difficulty)) {
                    filtered_questions.add(q);
                }
            }
        }

        return filtered_questions;
    }

    public Question getRandomQuestion() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return questions.get(random.nextInt(questions.size())); // todo: what if id doesn't start from 1
    }

    public Question getQuestionById(Integer id) {
        return questionRepository.findById(id).orElse(null);
    }

    // Category

    public Category addCategory(String name, String description, Integer created_by) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        category.setCreated_by(created_by);
        return categoryRepository.save(category);
    }

    public Boolean deleteCategory(Integer id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return null;
        }
        if (! category.getQuestions().isEmpty()) {
            return false;
        }
        categoryRepository.delete(category);
        return true;
    }

    public Category editCategory(Integer id, String name, String description, Integer created_by) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return null;
        }
        category.setName(name);
        category.setDescription(description);
        category.setCreated_by(created_by);
        return categoryRepository.save(category);
    }

    public Category getCategory(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findAll().stream()
                .filter(category -> category.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Category getCategoryById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<CategoryDTO2> getAllCategories() {
        List<CategoryDTO2> categoryDTOs = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            CategoryDTO2 dto = new CategoryDTO2(
                    category.getId(),
                    category.getName(),
                    category.getDescription(),
                    category.getQuestions().size()
            );
            categoryDTOs.add(dto);
        }
        return categoryDTOs;
    }

    public List<CategoryDTO2> getCategoriesByUser(Integer userId) {
        List<CategoryDTO2> categories = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            if (category.getCreated_by().equals(userId)) {
                CategoryDTO2 dto = new CategoryDTO2(
                        category.getId(),
                        category.getName(),
                        category.getDescription(),
                        category.getQuestions().size()
                );
                categories.add(dto);
            }
        }
        return categories;
    }

    // Answer

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
}
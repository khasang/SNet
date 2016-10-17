package io.khasang.snet.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import io.khasang.snet.entity.Question;
import io.khasang.snet.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/question")
public class RestUserController {
//    @AutowiredUserService
//    UserService userService;
    @Autowired
    QuestionService questionService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object getQuestion(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        try {
            int questionId = Integer.valueOf(inputId);
            Question question = questionService.getQuestionById(questionId);
            if (question != null) {
                return question;
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return "User with id: " + questionId + " not found.";
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Bad user id format: " + inputId;
        }
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Question> getAllQuestion() {
        return questionService.getQuetionList();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object addQuestion(@RequestBody Question question, HttpServletResponse response) {
        try {
            questionService.addQuestion(question);

            JsonFactory factory = new JsonFactory();
            JsonParser parser  = factory.createParser(response.toString());

            while(!parser.isClosed()){
                JsonToken jsonToken = parser.nextToken();

                System.out.println("jsonToken = " + jsonToken);
            }

            return question;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Error adding question: " + e.getMessage();
        }
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    @ResponseBody
    public Object updateQuestion(@RequestBody Question question, HttpServletResponse response) {
        try {
            questionService.updateQuestion(question);
            return question;
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return "Error updating user: " + e.getMessage();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public String deleteQuestion(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        try {
            int questionId = Integer.valueOf(inputId);
            Question question = questionService.getQuestionById(questionId);
            if (question != null) {
                questionService.deleteQuestion(question);
                return "Question #" + questionId + " successfully deleted.";
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return "Question with id: " + questionId + " not found.";
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Bad question id format: " + inputId;
        }
    }
}

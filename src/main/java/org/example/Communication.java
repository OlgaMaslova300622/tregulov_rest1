package org.example;

import com.sun.source.tree.ParameterizedTypeTree;
import org.example.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication { // c помощью объектов этого класса будем общаться с rest сервисом

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://localhost:8080/tregulov_rest/api/employees";


    public List<Employee> getAllEmployees() { // метод будет посылать http запрос и получать от rest сервиса список всех работников
                                // ResponseEntity - это обертка http response
        ResponseEntity<List<Employee>> responseEntity =  // ответ на наш http запрос
         restTemplate.exchange(URL, HttpMethod.GET, null,  // а это запрос
              new ParameterizedTypeReference<List<Employee>>() {} );  // вспомогательный класс для передачи дженерик типа (список работников)

                 // отправляем реквест и его результат получаем в responseEntity

        List<Employee> allEmployees = responseEntity.getBody(); // получаем пэйлот - полезную нагрузку - список всех работников

        return allEmployees;
    }



    public Employee getEmployee (int id) {  // получаем от rest сервиса конкретного работника по id
                                       // делаем запрос результатом которого будет получение работника
        Employee employee = restTemplate.getForObject(URL + "/" + id, Employee.class);

         return employee;
    }


    public void saveEmployee (Employee employee) {  //сохраняем нового работника или апдейтим уже существуещего
                                                  // если id равен 0, посылается http запрос на создание нового работника
                                                  // если id уже есть, то посылается запрос на обновление существующего
        int id = employee.getId();

        if (id == 0) {                             // создаем нового работника. Работаем с методом post
            ResponseEntity<String> responseEntity =  // указываем какого типа будет тело response. Будет json формат, потому указываем String
                    restTemplate.postForEntity(URL, employee, String.class);
            System.out.println("New employee was added to Database");
            System.out.println(responseEntity.getBody());

        } else {                        // обновляем существующего работника
            restTemplate.put(URL, employee);
            System.out.println("Employee with ID " + id + " was updated");
        }
    }



    public void deleteEmployee (int id) { // удаление работника
        restTemplate.delete(URL + "/" + id);
        System.out.println("Employee with ID " + id + " was deleted from DB");

    }








}

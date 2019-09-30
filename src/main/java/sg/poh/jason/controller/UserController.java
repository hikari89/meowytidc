package sg.poh.jason.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.poh.jason.data.SalaryService;
import sg.poh.jason.model.Result;

@RestController
public class UserController {

    @Autowired
    SalaryService service;

    @RequestMapping("/users")
    public Result users() {
        Result.LoadCSVToDB("salary.csv", service);
        return Result.FromDB(service);
    }

}

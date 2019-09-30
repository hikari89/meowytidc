package sg.poh.jason.model;

import sg.poh.jason.data.SalaryService;
import sg.poh.jason.utils.CSVFileHelper;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private final List<User> results;

    public Result(List<User> users) {
        this.results = users;
    }

    public static void LoadCSVToDB(String fileName, SalaryService service) {
        List<User> users = new ArrayList<User>();
        List<String> lines = CSVFileHelper.LoadFileAsList(fileName);
        User user = null;
        for (String line : lines) {
            user = User.CreateFromCSVLine(line);
            if (user != null && user.isValid()) {
                service.add(user);
            }
        }
    }

    public static Result FromDB(SalaryService service) {
        return new Result(service.getSalaries());
    }

    public List<User> getResults() {
        return results;
    }


}

package sg.poh.jason.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal salary;

    public User() {

    }

    public User(String name, String salaryStr) {
        this.name = name.trim();
        BigDecimal salaryVal = new BigDecimal(-1);
        try {
            salaryVal = (new BigDecimal(salaryStr.trim())).setScale(2, RoundingMode.HALF_UP);
        } catch (Exception e) {
            System.out.println("Unable to convert to BigDecimal: " + salaryStr.trim());
        } finally {
            this.salary = salaryVal;
        }
    }

    public static User CreateFromCSVLine(String line) {
        User user = null;
        int delimiterIdx = line.lastIndexOf(',');
        String name = line.substring(0, delimiterIdx);
        String salaryStr = line.substring(delimiterIdx + 1);
        return new User(name, salaryStr);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    protected boolean isValid() {
        return isSalaryValid() && isNameValid();
    }

    private boolean isNameValid() {
        return name != null && name.trim().length() > 0;
    }

    private boolean isSalaryValid() {
        return salary.doubleValue() >= 0 && salary.doubleValue() <= 4000;
    }
}

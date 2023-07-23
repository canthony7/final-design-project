import com.crud.management.ManagementApplication;
import com.crud.management.pojo.Fee;
import com.crud.management.repository.FeeRepository;
import com.crud.management.service.DealerService;
import com.crud.management.service.FeeService;
import com.crud.management.service.ProjectService;
import com.crud.vo.ResponseBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest(classes = ManagementApplication.class)
public class ManagementTest {

    @Resource
    DealerService dealerService;

    @Resource
    FeeService feeService;

    @Resource
    FeeRepository feeRepository;

    @Resource
    ProjectService projectService;

    @Test
    void Test01(){
        String startDateString = "2023-06-01";
        String endDateString = "2023-07-01";
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalDate endDate = LocalDate.parse(endDateString);
        ResponseBean bean = feeService.findFeeByMonth("推广费", startDate, endDate);
        System.out.println(bean);
    }

    @Test
    void Test02(){
        ResponseBean allType = feeService.findAllType();
        System.out.println(allType);
    }

    @Test
    void Test03(){
        List<Fee> feeList = feeRepository.findAll();
        for (Fee fee : feeList) {
            System.out.println(fee);
        }
    }

    @Test
    void Test04(){
        ResponseBean allProjects = projectService.findAllProjects();
        Object data = allProjects.getData();
        System.out.println(data);
    }

    @Test
    void Test05(){
        ResponseBean dealer = feeService.findFeeByDealer(1L);
        System.out.println(dealer);
    }

}

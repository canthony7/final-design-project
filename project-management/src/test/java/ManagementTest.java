import com.crud.management.ManagementApplication;
import com.crud.management.pojo.Dealer;
import com.crud.management.pojo.Fee;
import com.crud.management.pojo.Project;
import com.crud.management.repository.DealerRepository;
import com.crud.management.repository.FeeRepository;
import com.crud.management.repository.ProjectRepository;
import com.crud.management.service.DealerService;
import com.crud.management.service.FeeService;
import com.crud.management.service.ProjectService;
import com.crud.utils.DateUtils;
import com.crud.vo.ResponseBean;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

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

    @Resource
    ProjectRepository projectRepository;

    @Resource
    DealerRepository dealerRepository;

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

    @Test
    void Test06(){
        Optional<Project> optionalProject = projectRepository.findById(1L);
        if (optionalProject.isPresent()){
            Project project = optionalProject.get();
            System.out.println(project);
        }
    }

    @Test
    void Test07(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Dealer> dealerList1 = dealerRepository.findDealerByEnable(pageRequest, 1);
        List<Dealer> dealerList = dealerList1.getContent();
        for (Dealer dealer : dealerList) {
            System.out.println(dealer);
        }
    }

    @Test
    void Test08(){
        ResponseBean result = feeService.findFeeByProject(2L);
        Object data = result.getData();
        System.out.println(data.toString());
    }

    @Test
    void Test09(){
        Optional<Project> optionalProject = projectRepository.findById(19L);
        if (optionalProject.isPresent()){
            Project project = optionalProject.get();
            System.out.println();
        }
    }

}

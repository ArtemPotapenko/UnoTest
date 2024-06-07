import org.example.services.GroupService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupServiceTest {
    private final GroupService service= new GroupService();
    @Test
    void oneGroupTest(){
        service.addNewString("1;1;1");
        service.addNewString("1;2;2");
        service.addNewString("2;2;2");
        service.formGroups();
        Assertions.assertEquals(service.getGroups().size(), 1);
        Assertions.assertEquals(service.getGroups().get(0).size(), 3);
    }
    @Test
    void oneGroupAndEqualStringTest(){
        service.addNewString("1;1;1");
        service.addNewString("1;1;1");
        service.formGroups();
        Assertions.assertEquals(service.getGroups().size(), 1);
        Assertions.assertEquals(service.getGroups().get(0).size(), 1);
        Assertions.assertEquals(service.getGroups().get(0).get(0), "1;1;1");
    }
    @Test
    void twoGroupTest(){
        service.addNewString("1;1;1");
        service.addNewString("1;3;1");
        service.addNewString("2;2;2");
        service.addNewString("2;4;;");
        service.addNewString("3;3;;");
        service.addNewString("2;4;4");
        service.addNewString("2;5;5");

        service.formGroups();

        Assertions.assertEquals(service.getGroups().size(), 2);
        Assertions.assertEquals(service.getGroups().get(0).size(), 4);
        Assertions.assertEquals(service.getGroups().get(1).size(),3);
    }
    @Test
    void bigUnionGroupTest(){

        service.addNewString("1;1;1");
        service.addNewString("1;3;1");
        service.addNewString("2;2;2");
        service.addNewString("2;4;;");
        service.addNewString("3;3;;");
        service.addNewString("2;4;4");
        service.addNewString("2;5;5");
        service.addNewString("2;3;3"); //Это строка должна объединить группы

        service.formGroups();

        Assertions.assertEquals(service.getGroups().size(), 1);
        Assertions.assertEquals(service.getGroups().get(0).size(),8);
    }
}

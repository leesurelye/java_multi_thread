package serializable;

import org.common.obj.Tool;
import org.mock.obj.TestA;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.HashMap;

public class TestSerializable
{

    @Test
    public void testSerializableCase1(){
        System.out.println("aaaa");
    }
    @Test
    public void testMockito()
    {
        // 如果直接使用对象，则会返回原始方法的返回值
        TestA originA = new TestA();
        System.out.println(originA.test());
        // 如果Mockito，则会返回原始方法的返回值
        TestA testA = Mockito.mock(TestA.class);
        Mockito.when(testA.test()).thenReturn(new HashMap<>());
        System.out.println(testA.test());
    }
    @Test
    public void testObject()
    {
        Tool tool = new Tool("abc");
        System.out.println("testModifyObject: tool hashcode = " + tool.hashCode());
        System.out.println("[Before modified: ] " + tool);
        testModifyObject(tool);
        System.out.println("[After modified: ] " + tool);
    }
    @Test
    public void testObject1()
    {
        Tool tool;
        tool = new Tool("abc");
        System.out.println("testModifyObject: tool hashcode = " + tool.hashCode());
        System.out.println("[Before modified: ] " + tool);
        testModifyObject1(tool);
        System.out.println("[After modified: ] " + tool.hashCode());
    }

    private Tool testModifyObject1(Tool tool)
    {
        tool.setName("modify Name");
        return tool;
    }
    private void testModifyObject(Tool tool)
    {
        System.out.println("testModifyObject: tool hashcode = " + tool.hashCode());
        tool.setName("modify Name");
    }
}

package serializable;

import javafx.util.Pair;
import org.common.obj.Tool;
import org.mock.obj.TestA;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestSerializable
{

    @Test
    public void testSerializableCase1()
    {
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

    @Test
    public void testHashMap()
    {
        List<String> a = new ArrayList<>();
        a.add("abcdabdabdbnad");
        a.add("badbansdbas");
        List<String> b = new ArrayList<>();
        b.add("abcdabdabdbnad");
        b.add("badbansdbas");
        System.out.println(a.equals(b));
    }

    /**
     * 对于浮点数的比较，不建议使用==和equals
     */
    @Test
    public void testErrorFloat()
    {
        float a = 1.0F - 0.9F;
        float b = 0.9F - 0.8F;
        if (a == b) {
            System.out.println("a == b");
        } else {
            System.out.println(" a != b");
        }
        Float x = Float.valueOf(a);
        Float y = Float.valueOf(b);
        if (x.equals(y)) {
            System.out.println("Float x == y");
        } else {
            System.out.println("Float x != y");
        }
    }

    /**
     * 浮点数的比较，建议指定一个范围，若两个浮点数的误差在此范围内，则认为是相等的
     */
    @Test
    public void testRightFloat()
    {
        float a = 1.0F - 0.9F;
        float b = 0.9F - 0.8F;
        float diff = 1E-6F;
        if (Math.abs(a - b) < diff) {
            System.out.println("a == b");
        } else {
            System.out.println("a != b");
        }
    }
    @Test
    public void testBigDecimal()
    {
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");
        BigDecimal x = a.subtract(b);
        BigDecimal y = b.subtract(c);
        if (x.compareTo(y) == 0) {
            System.out.println("x == y");
        } else {
            System.out.println("x != y");
        }
    }
    @Test
    public void testSplit()
    {
        String str = "a,b,c,,";
        String[] split = str.split(",");
        System.out.println(split.length);
    }
    @Test
    public void testToMap()
    {
        List<Pair<String, Double>> pairList = new ArrayList<>();
        pairList.add(new Pair<>("version1", 12.10));
        pairList.add(new Pair<>("version2", 12.19));
        pairList.add(new Pair<>("version2", 6.28));

        Map<String, Double> map = pairList.stream()
                // 注意 toMap方法，当value为空时，会抛NPE
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue, Double::sum));
        System.out.println(map);
    }
    @Test
    public void testRemove()
    {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        // 不要在遍历数组的时候删除数组
//        for (String item : list) {
//            if ("2".equals(item)) {
//                list.remove(item);
//            }
//        }
        // 删除元素使用迭代器模式
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("2".equals(item)) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }
}

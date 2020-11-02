package com.config.server.test;

import com.config.server.pojo.User;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Map.Entry.comparingByValue;

/**
 * @description:
 * @author: LiWei
 * @date: 2020/10/14 10:15
 */
public class test {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        User build3 = User.builder().age(25).score(99.6).build();
        User build2 = User.builder().age(75).score(120).build();
        User build = User.builder().age(15).score(99.6).build();
        User build1 = User.builder().age(24).score(55.9).build();
        userList.add(build2);
        userList.add(build);
        userList.add(build1);
        userList.add(build3);
        rank(userList);
        ;
        Map<String, Object> maps = new HashMap<>();
        maps.put("wzn", (int) System.currentTimeMillis() + 100);
        maps.put("xhf", (int) System.currentTimeMillis() + 22);
        maps.put("ggg", (int) System.currentTimeMillis() + 66);


        LinkedHashMap collect1 = maps.entrySet().stream().sorted((s1, s2) -> -Integer.compare((Integer) s1.getValue(), (Integer) s2.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        collect1.forEach((k,v)->{
            System.out.println(k);
            System.out.println(v);
        });
        System.out.println("正序排序数据:" + collect1.toString());

        List<User> collect = userList.stream()
                .filter(user -> user.getAge() > 25)
                .collect(Collectors.toList());
        System.out.println(collect);
//        userList.sort(comparing(User::getAge).reversed());
//        System.out.println("按年龄排序:");
//        Iterator<User> iterator_score = userList.iterator();
//        while(iterator_score.hasNext()) {
//            System.out.println(iterator_score.next()+" ");
//        }


    }

    public static void rank(List<User> users) {
        List<Entry<Double, List<User>>> list = users.stream()
                .collect(Collectors.groupingBy(User::getScore))
                .entrySet()
                .stream()
                .sorted((s1, s2) -> -Double.compare(s1.getKey(), s2.getKey()))
                .collect(Collectors.toList());
        int index = 1;
        int[] indexArray = new int[]{index};
        for (Entry<Double, List<User>> entry : list) {

            entry.getValue().forEach((s) -> {
                System.out.println("名次:" + indexArray[0] + "\t分数:" + entry.getKey() + "\t年龄:" + s.getAge());
                System.out.println();
            });
            indexArray[0] = indexArray[0] + entry.getValue().size();

//            System.out.print("名次:" + index + "\t分数:" + entry.getKey() + "\t年龄");
//            entry.getValue().forEach((s) -> System.out.print("  " + s.getAge()));
//            System.out.println();
//            index = index + entry.getValue().size();
        }

    }
}

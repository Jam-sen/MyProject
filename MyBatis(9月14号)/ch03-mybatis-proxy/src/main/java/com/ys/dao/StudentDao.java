package com.ys.dao;

import com.ys.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

//接口操作student表
public interface StudentDao {
    //查询student表的所有数据
    List<Student> selectStudents();

    /**
     * 插入数据方法
     * @param student
     * @return插入成功条数
     */
    int insertStudent(Student student);

    /**
     * 更新数据方法
     * @param student
     * @return更新数据条数
     */
    int updateStudent(Student student);

    /**
     * 根据id查单条
     * @param id 学生id
     * @return
     */
    HashMap selectStudentById(int id);

    /**
     * 根据多个条件查找数据
     * @param age 学生年龄
     * @param name 学生姓名
     * @return
     */
    List<Student> selectStudentToMultipleConditions(@Param ("MyAge") int age,
                                                    @Param ("MyName") String name);

    /**
     * 根据保存参数值的对象查数据
     * @param student
     * @return
     */
    List<Student> selectStudentToJavaClass(Student student);

    /**
     * 根据参数位置传递参数，并查询
     * @return
     * @param age 学生年龄
     * @param name 学生姓名
     */
    List<Student> selectStudentToPlace(int age , String name);

    /**
     * 使用map传递参数，并查询
     * @param hashMap 参数集合
     */
    List<Student> selectStudentToMap(HashMap<String, Object> hashMap);

    /**
     * 根据id查单条(使用ResultMap)
     * @param id
     * @return
     */
    List<Student> selectUseResultMap(int id);

    /**
     * 使用动态sql
     */
    List<Student> selectStudentIf(Student student);

    /**
     * 动态sql之<where>
     */
    List<Student> selectStudentWhere(Student student);

    /**
     * 动态sql之<foreach>
     */
    List<Student> selectStudentForeach(List<Integer> idList);

    /**
     * 动态sql之foreach,遍历list<Student>
     */
    List<Student> selectStudentForeach2 (List<Student> studentList);

    /**
     * 动态sql之代码片段
     */
    Student selectStudentSqlFragment(Integer id);
}

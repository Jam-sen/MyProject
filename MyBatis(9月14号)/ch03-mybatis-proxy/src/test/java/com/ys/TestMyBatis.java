package com.ys;


import com.github.pagehelper.PageHelper;
import com.ys.dao.StudentDao;
import com.ys.domain.Student;
import com.ys.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class TestMyBatis {
    private SqlSession sqlSession = MyBatisUtil.getSqlSession ();

    private StudentDao studentDao = sqlSession.getMapper (StudentDao.class);
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testSelectStudents() {
        PageHelper.startPage (2,3);
        List<Student> list = studentDao.selectStudents ();
        for (Student s : list) {
            System.out.println (s);
        }
        sqlSession.close ();
    }

    @Test
    public void testInsertStudent() {
        Student student = new Student ();
        student.setEmile ("zhaoyun@qq.com");
        student.setId (1022);
        student.setAge (43);
        student.setName ("赵云");
        studentDao.insertStudent (student);
        sqlSession.commit ();
        sqlSession.close ();
    }

    @Test
    public void testUpdateStudent(){
        Student student = new Student ();
        student.setName ("王五");
        student.setEmile ("wangwu@qq.com");
        student.setAge (18);
        student.setId (1003);
        studentDao.updateStudent (student);
        sqlSession.commit ();
        sqlSession.close ();
    }

    @Test
    public void testSelectStudentById(){
        HashMap hashMap = studentDao.selectStudentById (1001);
        System.out.println (hashMap);
    }

    @Test
    public void testSelectStudentToMultipleConditions(){
        List<Student> list = studentDao.selectStudentToMultipleConditions (20,"张飞");
        list.forEach (student -> System.out.println (student));
    }

    @Test
    public void testSelectStudentToJavaClass(){
        Student stu = new Student ();
        stu.setAge (20);
        stu.setName ("张飞");
        List<Student> list = studentDao.selectStudentToJavaClass (stu);
        list.forEach (student -> System.out.println (student.toString ()));
    }

    @Test
    public void testSelectStudentToPlace(){
        List<Student> list = studentDao.selectStudentToPlace (20,"张飞");
        list.forEach (student -> System.out.println (student.toString ()));
    }

    @Test
    public void testSelectStudentToMap(){
        HashMap<String,Object> hashMap = new HashMap<> ();
        hashMap.put ("myname","张飞");
        hashMap.put ("myage","20");
        List<Student> list = studentDao.selectStudentToMap (hashMap);
        list.forEach (student -> System.out.println (student.toString ()));
    }

    @Test
    public void testSelectUseResultMap(){
        List<Student> list = studentDao.selectUseResultMap (1001);
        list.forEach (student -> System.out.println (student));
    }

    @Test
    public void testSelectStudentIf(){
        Student student = new Student ();
        student.setName ("张飞");
        student.setAge (12);
        List<Student> list =studentDao.selectStudentIf (student);
    }

    @Test
    public void testSelectStudentWhere(){
        Student student = new Student ();
        student.setName ("张飞");
        student.setAge (21);
        List<Student> list =studentDao.selectStudentWhere (student);
    }

    @Test
    public void testSelectStudentForeach() throws ExceptionOne {
        List<Integer> list = new ArrayList<> ();
        list.add (1001);
        list.add(1002);
        list.add (1003);
        list.add(1004);
        System.out.println (list.size ());
        if(list.size ()==0 || list==null){
            throw new ExceptionOne ("查询信息不能为空");
        }
        studentDao.selectStudentForeach (list);
    }

    @Test
    public void testSelectStudentForeach2(){
        List<Student> list = new ArrayList<> ();
        Student student = new Student ();
        student.setId (1005);
        Student student2 = new Student ();
        student2.setId (1006);
        Student student3 = new Student ();
        student3.setId (1007);
        Student student4 = new Student ();
        student4.setId (1008);
        list.add (student);
        list.add (student2);
        list.add (student3);
        list.add (student4);
        studentDao.selectStudentForeach2 (list);
    }

    @Test
    public void selectStudentSqlFragment(){
        studentDao.selectStudentSqlFragment (1001);
    }


}

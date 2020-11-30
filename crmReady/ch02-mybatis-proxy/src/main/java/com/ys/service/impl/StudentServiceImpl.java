package com.ys.service.impl;

import com.ys.dao.StudentDao;
import com.ys.domain.Student;
import com.ys.service.StudentService;
import com.ys.util.MybatisUtil;
import com.ys.vo.StudentAndClassVo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = MybatisUtil.getSqlSession().getMapper(StudentDao.class);

    @Override
    public List<Map<Object, Object>> selectStudents() {

        return studentDao.selectStudents();
    }

    @Override
    public void insertStudent(Student student) {
        studentDao.insertStudent(student);
    }

    @Override
    public Student selectById(int id) {
        return studentDao.selectById(id);

    }

    @Override
    public String service1(Student student) {
        studentDao.insertStudent(student);
        Student student1 = studentDao.selectById(student.getId());
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("添加的学生是：" + student.getName());
        stringBuffer.append("\r\n");
        stringBuffer.append("查询此学生信息：" + student1);

        List<Map<Object,Object>> sList = studentDao.selectStudents();
        for (Map<Object, Object> map : sList) {
            stringBuffer.append("\r\n");
//----------->map的entrySet遍历方法
            Set<Map.Entry<Object, Object>> entrySet = map.entrySet();
            for (Iterator iterator = entrySet.iterator(); iterator.hasNext();) {
                Map.Entry node = (Map.Entry) iterator.next();
                System.out.println("9999999"+node.getKey()+"=="+node.getValue());
            }
//----------------------------------------
            Set<Object> mapSet = map.keySet();
            for (Object key: mapSet) {
                stringBuffer.append(key+" = ").append(map.get(key)+"  ");
            }
            stringBuffer.append("\r\n------------------------");
        }
        stringBuffer.append("\r\n===========================");
        stringBuffer.append("查询全部学生：" + sList);
        return stringBuffer.toString();

    }

    @Override
    public String selectStudentClass() {
        List<Map<String, Object>> maps = studentDao.selectStudentClass();
        StringBuilder stringBuilder = new StringBuilder();
        for (Map<String,Object> map :maps) {
            Set<String> keySet = map.keySet();
            for (String key : keySet) {
                Object value = map.get(key);
                stringBuilder.append(key+"="+value+" ");
            }
            stringBuilder.append("\r\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public String selectStudentClass2() {
        List<StudentAndClassVo> voList = studentDao.selectStudentClass2();
        StringBuilder stringBuilder = new StringBuilder();
        for (StudentAndClassVo vo : voList) {
            stringBuilder.append(vo);
        }
        return stringBuilder.toString();
    }
}

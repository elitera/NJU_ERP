package com.nju.edu.erp.service.Impl;

import com.nju.edu.erp.dao.EmployeeDao;
import com.nju.edu.erp.exception.MyServiceException;
import com.nju.edu.erp.model.po.EmployeePO;
import com.nju.edu.erp.model.po.EmployeePunchPO;
import com.nju.edu.erp.model.vo.employee.EmployeePunchVO;
import com.nju.edu.erp.model.vo.employee.EmployeeVO;
import com.nju.edu.erp.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) { this.employeeDao = employeeDao; }

    @Override
    public void addEmployee(EmployeeVO employeeVO) {
        EmployeePO employeePO = employeeDao.findEmployeeById(employeeVO.getId());
        if (employeePO != null) {
            throw new MyServiceException("A0003", "员工已存在");
        }
        EmployeePO employeeSave = new EmployeePO();
        BeanUtils.copyProperties(employeeVO, employeeSave);
        employeeDao.createEmployee(employeeSave);
    }

    @Override
    public void deleteEmployeeById(Integer id) {
        EmployeePO employeePO = employeeDao.findEmployeeById(id);
        if (employeePO != null) {
            employeeDao.deleteEmployeeById(id);
        }
    }

    @Override
    public void updateEmployee(EmployeeVO employeeVO) {
        EmployeePO employeePO = employeeDao.findEmployeeById(employeeVO.getId());
        if (employeePO == null) {
            throw new MyServiceException("A0003", "员工已存在");
        }
        EmployeePO employeeUpdate = new EmployeePO();
        BeanUtils.copyProperties(employeeVO, employeeUpdate);
        employeeDao.updateEmployee(employeeUpdate);
    }

    @Override
    public void addPunch(EmployeePunchVO employeePunchVO) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(employeePunchVO.getPunchTime());
        List<EmployeePunchPO> employeePunchPOS = employeeDao.getPunchByEmployeeName(employeePunchVO.getName());
        for (EmployeePunchPO punchPO : employeePunchPOS) {
            String punchDate = format.format(punchPO.getPunchTime());
            if (date.equals(punchDate)) {
                throw new MyServiceException("A0004", "员工"+employeePunchVO.getName()+"今日已打卡");
            }
        }
        EmployeePunchPO employeePunchSave = new EmployeePunchPO();
        BeanUtils.copyProperties(employeePunchVO, employeePunchSave);
        employeeDao.punch(employeePunchSave);
    }

    @Override
    public List<EmployeeVO> getALLEmployees() {
        List<EmployeePO> employeePOS = employeeDao.findAllEmployees();
        List<EmployeeVO> employeeVOS = new ArrayList<>();
        for (EmployeePO employeePO : employeePOS) {
            EmployeeVO employeeVO = new EmployeeVO();
            BeanUtils.copyProperties(employeePO, employeeVO);
            employeeVOS.add(employeeVO);
        }
        return employeeVOS;
    }

    @Override
    public String getEmployeeSalaryGrantingModeById(Integer id) {
        EmployeePO employeePO = employeeDao.findEmployeeById(id);
        return employeePO.getSalaryGrantingMode();
    }

    @Override
    public String getEmployeeSalaryCalculatingModeById(Integer id) {
        EmployeePO employeePO = employeeDao.findEmployeeById(id);
        return employeePO.getSalaryCalculatingMode();
    }

    public void setEmployeeSalaryCalculatingModeById(Integer id, String mode) {
        EmployeePO employeePO = EmployeePO.builder().id(id).salaryCalculatingMode(mode).build();
        employeeDao.updateEmployee(employeePO);
    }

    @Override
    public int getPunchedTimesByEmployeeName(String name) {
        List<EmployeePunchPO> employeePunchPOS = employeeDao.getPunchByEmployeeName(name);
        return employeePunchPOS.size();
    }

    @Override
    public List<EmployeePunchVO> showPunchByEmployeeName(String name) {
        List<EmployeePunchPO> employeePunchPOS = employeeDao.getPunchByEmployeeName(name);
        List<EmployeePunchVO> employeePunchVOS = new ArrayList<>();
        for (EmployeePunchPO employeePunchPO : employeePunchPOS) {
            EmployeePunchVO employeePunchVO = new EmployeePunchVO();
            BeanUtils.copyProperties(employeePunchPO, employeePunchVO);
            employeePunchVOS.add(employeePunchVO);
        }
        return employeePunchVOS;
    }
}

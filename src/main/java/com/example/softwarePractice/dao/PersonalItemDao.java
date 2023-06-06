package com.example.softwarePractice.dao;

import com.example.softwarePractice.controller.PersonalItem.PersonalItemRequest;
import com.example.softwarePractice.domain.ItemStatus;
import com.example.softwarePractice.domain.PersonalItem;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PersonalItemDao {
    @PersistenceContext
    private EntityManager em;

    public List<PersonalItem> findAll() {
        return em.createQuery("select p from PersonalItem p", PersonalItem.class).getResultList();
    }


    @Transactional
    public PersonalItem insertItem(PersonalItem personalItem) throws DataAccessException {
        em.persist(personalItem);
        return personalItem;
    }

    @Transactional
    public PersonalItem updateItem(PersonalItemRequest itemRegistReq) throws DataAccessException {
        PersonalItem personalItem = em.find(PersonalItem.class, itemRegistReq.getItemId());
        personalItem.setTitle(itemRegistReq.getTitle());
        personalItem.setPrice(itemRegistReq.getPrice());
        personalItem.setDescription(itemRegistReq.getDescription());
        if (itemRegistReq.getStatus().equals("거래가능")) {
            personalItem.setStatus(ItemStatus.INSTOCK);
        } else if (itemRegistReq.getStatus().equals("거래중")) {
            personalItem.setStatus(ItemStatus.ING);
        } else {
            personalItem.setStatus(ItemStatus.SOLDOUT);
        }

        return personalItem;
    }

    @Transactional
    public void deleteItem(PersonalItem personalItem) {
        if (em.contains(personalItem)) {
            // Persistence Context 영역에 존재하지 않을 경우 예외 발생하므로 존재 여부 확인 후 remove
            em.remove(personalItem);
        } else {
            // 없을 경우, em.merge를 통하여 객체가 Persistence Context 영역에 분리된 상태를
            // Persistence Context 영역에 영속상태로 유지하고 remove 함.
            em.remove(em.merge(personalItem));
        }
    }
}

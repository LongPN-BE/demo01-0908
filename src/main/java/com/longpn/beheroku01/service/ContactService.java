package com.longpn.beheroku01.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.longpn.beheroku01.model.Contact;


@Repository
public interface ContactService extends JpaRepository<Contact, Long>{
    //todo
}

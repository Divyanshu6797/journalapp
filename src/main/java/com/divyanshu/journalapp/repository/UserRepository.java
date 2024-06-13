package com.divyanshu.journalapp.repository;

import com.divyanshu.journalapp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    <string> User findByUserName(string userName);
}

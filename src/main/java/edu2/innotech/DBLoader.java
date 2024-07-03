package edu2.innotech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Consumer;

@Component
public class DBLoader implements Consumer {
    @Autowired
    UsersRepo usersRepo;

    @Autowired
    LoginsRepo loginsRepo;

    private HashMap<Users, List<Logins>> convertToHashMap(List<LogLoadData> lldList) {
        HashMap<Users, List<Logins>> hm = new HashMap<>();
        for (LogLoadData lld: lldList) {
            Users u = new Users(null, lld.username, lld.f + " " + lld.i + " " + lld.o);
            Logins l = new Logins(null, lld.access_date, null, lld.application);

            if (hm.containsKey(u)) {
                hm.get(u).add(l);
            } else {
                List<Logins> ll = new ArrayList<>();
                ll.add(l);
                hm.put(u, ll);
            }
        }
        return hm;
    }

    @Override
    public void accept(Object o) {
        List<LogLoadData> lldList = (List<LogLoadData>) o;

        HashMap<Users, List<Logins>> hm = convertToHashMap(lldList);

        for (Map.Entry<Users, List<Logins>> entry : hm.entrySet()) {
            Users u = entry.getKey();
            List<Logins> loginsList = entry.getValue();

            //Users u1 = usersRepo.findByUserName(u.username);
            //if (!u1.equals(null)) { u.id = u1.id; }
            u = usersRepo.save(u);

            for (Logins l: loginsList) {
                l.user = u;
                loginsRepo.save(l);
            }
        }
    }
}

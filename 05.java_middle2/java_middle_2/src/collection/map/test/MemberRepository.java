package collection.map.test;

import java.util.HashMap;
import java.util.Map;

public class MemberRepository {
    private Map<String, Member> memberMap = new HashMap<>();
    public void save(Member member) {
        memberMap.put(member.getId() , member);
    }
    public void remove(String id) {
        memberMap.remove(id);
        // 코드 작성
    }
    public Member findById(String id) {
        return memberMap.get(id);
        // 코드 작성
    }
    public Member findByName(String name) {
        for (Map.Entry<String, Member> entry : memberMap.entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                return entry.getValue();
            }
        }
        return null;
    }
}

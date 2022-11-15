package net.huray.backend.minuting.service.user;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.huray.backend.minuting.account.Member;
import net.huray.backend.minuting.account.MemberRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserComponent {

    private final MemberRepository memberRepository;

    public Member get(UUID uid){
        return memberRepository.findById(uid).orElseThrow();
    }

}

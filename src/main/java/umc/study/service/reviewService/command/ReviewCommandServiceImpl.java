package umc.study.service.reviewService.command;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.apiPayload.exception.handler.MemberHandler;
import umc.study.apiPayload.exception.handler.StoreHandler;
import umc.study.domain.Member;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.request.ReviewRequestDTO;
import umc.study.repository.ReviewRepository;

@AllArgsConstructor
@Service
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public void addReview(ReviewRequestDTO.CreateDTO dto) {
        Store store = storeRepository.findById(dto.getMemberId()).orElseThrow(()->
                new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow(()->
                new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Review review = reviewRepository.save(dto.toReview(store, member));
    }
}

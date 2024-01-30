package com.teamsparta.todo.domain.todo.model

import com.fasterxml.jackson.annotation.JsonCreator
import org.apache.commons.lang3.EnumUtils

enum class DoneStatus {
    TRUE,
    FALSE;

    // @RequestParam(value = "done_status", required = false) status: String?, 이렇게 String 으로 컨트롤러에서 받는 게 아닌
    // Enum 으로 바로 받아서 String 으로 인식하게끔 컨트롤러가 설정할 수 있다.
    // DTO에서 특정 값을 Enum으로 받는 경우 스프링에서 기본적으로 제공하는 HandlerMethodArgumentResolver 구현에 의해 문자열은 각 Enum 타입으로 변경해 줍니다.
    //
    //하지만 문자열이 Enum 클래스의 멤버명과 정확히 일치해야만 변환을 해 주고 그렇지 않은 경우에는 오류가 발생하거나(이름이 멤버에 없음), 무시하게 됩니다.
    //
    //무시하는 경우는 비교적 괜찮지만, application/x-www-form-urlencoded 로 받는 경우 멤버에 없는 이름을 받는 경우 스프링이 만들어낸 예외가 발생하기 때문에 사용자에게 친절하지 않은 메시지가 노출될 가능성이 있습니다.
    companion object {
        @JvmStatic      // 자바 static 클래스 형태로 사용할 수 있게 만듦 => 인스턴스 생성을 안해도 바로 호출 할 수 있다는 특징이 있다.
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)    // json 으로부터 객체를 변환해줄 때 사용. 오브젝트 매핑할 때 썼었다.,
        fun parse(name: String?): DoneStatus? =
            name?.let { EnumUtils.getEnumIgnoreCase(DoneStatus::class.java, it.trim()) }
    }
    // 이렇게 싱글톤을 스태틱 클래스화 시키면 자바 팩토리가 사용하는 QueryDsl, 즉 자바쪽에서 이넘을 바로 쿼리디에셀을 이용하는 컨트롤러에서 서비스로 안넘어가고 컨트롤러에서 바로 쓸 수가 있다.
    //
}
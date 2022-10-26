package domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Member {
	private int memberNo;
	private String id, pw, name, email, registedDate;
}

// [Window] - [show view] - outline으로 뭐가 만들어져있는지 확인해 볼 수 있다.
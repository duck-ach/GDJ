package common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ActionForward {
	private String view;
	private boolean isRedirect;
}

package com.prakash.cd;

public class SException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SException() {

	}

	public SException(BusinessExceptionCode bec) {
		super(bec.description);
	}

	public enum BusinessExceptionCode {
		INVALID_FILE_PATH("File Path Is Invalid"), INVALID_FILE_STRUCTURE(
				"File Structure Not Valid"), UNSUPPORTED_FILE_SEPRATOR(
						"File Seprator Passed Not Suppported"), INVALID_COMMAND(
								"Invalid Comand Passed"), INVALID_COMMAND_ARGUMENT(
										"Invlaid or Wrong Argument passed"), NULL_COMMAND(
												"Command Passed Is NULL"), INVALID_PROCESS_OPTION(
														"Invalid  Process  Option"), ITEM_SIZE_GT_10("Name Can not Be Greater Than 10");
		private String description;

		BusinessExceptionCode(String description) {
			this.description = description;
		}

		public String getDesc() {
			return this.description;
		}

	}

}

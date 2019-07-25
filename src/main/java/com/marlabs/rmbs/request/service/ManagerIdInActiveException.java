package com.marlabs.rmbs.request.service;

public class ManagerIdInActiveException extends Exception {
		private static final long serialVersionUID = 1L;
		private String s;

		ManagerIdInActiveException(String s){
			this.s=s;
		}

		@Override
		public String toString() {
			return "ManagerIdInActiveException [s=" + s + "]";
		}
	}


package com.joke.bamenshenqi.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class ShellUtils {
	public final class CheckShellThread extends Thread {
		private int delaySeconeds;
		private Process process;
		private boolean success;

		public CheckShellThread(Process process, int delaySeconeds) {
			super();
			this.delaySeconeds = delaySeconeds;
			this.process = process;
		}

		public boolean isSuccess() {
			return this.success;
		}

		public void run() {
			try {
				Thread.sleep(((long) (this.delaySeconeds * 1000)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (this.process.exitValue() == 0) {
				this.success = true;
			}
		}
	}

	public static class CommandResult {
		public String errorMsg;
		public String successMsg;
		public int result;

		public CommandResult(int result, String successMsg, String errorMsg) {
			super();
			this.result = result;
			this.successMsg = successMsg;
			this.errorMsg = errorMsg;
		}

		public CommandResult(int result) {
			super();
			this.result = result;
		}
	}

	public static final String COMMAND_EXIT = "exit\n";
	public static final String COMMAND_LINE_END = "\n";
	public static final String COMMAND_SH = "sh";
	public static final String COMMAND_SU = "su";

	public ShellUtils() {
		super();
	}

	public static boolean checkRootPermission() {
		boolean v0 = true;
		if (ShellUtils.execCommand("echo root", true, false).result != 0) {
			v0 = false;
		}

		return v0;
	}

	public static CommandResult execCommand(String command, boolean isRoot, boolean isNeedResultMsg) {
		return ShellUtils.execCommand(new String[] { command }, isRoot, isNeedResultMsg);
	}

	public static CommandResult execCommand(String command, boolean isRoot) {
		return ShellUtils.execCommand(new String[] { command }, isRoot, true);
	}

	public static CommandResult execCommand(String[] commands, boolean isRoot, boolean isNeedResultMsg) {
		InputStream v18_2;
		String v13;
		CommandResult v18_3 = null;
		InputStream v19_2 = null;
		int v12 = -1;
		try {
			if (commands == null || commands.length == 0) {
				v18_3 = new CommandResult(v12, null, null);
			} else {
				BufferedReader v16 = null;
				BufferedReader v7 = null;
				StringBuilder v14 = null;
				StringBuilder v5 = null;
				Runtime v19 = Runtime.getRuntime();
				String v18 = isRoot ? "su" : "sh";
				Process v11 = v19.exec(v18);
				DataOutputStream v10 = new DataOutputStream(v11.getOutputStream());
				int v19_1 = commands.length;
				int v18_1;
				for (v18_1 = 0; v18_1 < v19_1; ++v18_1) {
					String v3 = commands[v18_1];
					if (v3 != null) {
						v10.write(v3.getBytes());
						v10.writeBytes("\n");
						v10.flush();
					}
				}

				v10.writeBytes("exit\n");
				v10.flush();
				if (isNeedResultMsg) {
					v12 = v11.waitFor();
					StringBuilder v15 = new StringBuilder();
					StringBuilder v6 = new StringBuilder();
					BufferedReader v17 = new BufferedReader(new InputStreamReader(v11.getInputStream()));
					v19_2 = v11.getErrorStream();
					BufferedReader v8 = new BufferedReader(new InputStreamReader(v19_2));
					while (true) {
						v13 = v17.readLine();
						if (v13 != null) {
							v15.append(v13);
							continue;
						}

						break;
					}

					while (true) {
						v13 = v8.readLine();
						if (v13 == null) {
							break;
						}

						v6.append(v13);
					}

					v5 = v6;
					v14 = v15;
					v7 = v8;
					v16 = v17;
				}

				if (v10 != null) {
					v10.close();
				}

				if (v16 != null) {
					v16.close();
				}

				if (v7 != null) {
					v7.close();
				}

				if (v11 != null) {
					v11.exitValue();
				}

				String v20 = v14 == null ? null : v14.toString();
				v18 = v5 == null ? null : v5.toString();

				v18_2 = v19_2;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return v18_3;
	}

	public static CommandResult execCommand(List arg2, boolean isRoot) {
		Object[] v0_1 = null;
		if (arg2 == null) {
			String[] v0 = null;
		} else {
			v0_1 = arg2.toArray(new String[0]);
		}

		return ShellUtils.execCommand(((String[]) v0_1), isRoot, true);
	}

	public static CommandResult execCommand(List arg1, boolean isRoot, boolean isNeedResultMsg) {
		Object[] v0_1 = null;
		if (arg1 == null) {
			String[] v0 = null;
		} else {
			v0_1 = arg1.toArray(new String[0]);
		}

		return ShellUtils.execCommand(((String[]) v0_1), isRoot, isNeedResultMsg);
	}

	public static CommandResult execCommand(String[] commands, boolean isRoot) {
		return ShellUtils.execCommand(commands, isRoot, true);
	}
}

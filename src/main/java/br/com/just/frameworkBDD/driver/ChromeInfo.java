package br.com.just.frameworkBDD.driver;

import java.io.File;
import java.io.IOException;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.VerRsrc;
import com.sun.jna.platform.win32.Version;
import com.sun.jna.platform.win32.WinBase;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public class ChromeInfo {

	private short[] fileversion = new short[4];
	private short[] productversion = new short[4];

	public ChromeInfo(File fileToCheck) throws IOException {

		int infoSize = Version.INSTANCE.GetFileVersionInfoSize(fileToCheck.getAbsolutePath(), null);
		Pointer buffer = Kernel32.INSTANCE.LocalAlloc(WinBase.LMEM_ZEROINIT, infoSize);

		try {
			Version.INSTANCE.GetFileVersionInfo(fileToCheck.getAbsolutePath(), 0, infoSize, buffer);
			IntByReference outputSize = new IntByReference();
			PointerByReference pointer = new PointerByReference();
			Version.INSTANCE.VerQueryValue(buffer, "\\", pointer, outputSize);
			VerRsrc.VS_FIXEDFILEINFO fileInfoStructure = new VerRsrc.VS_FIXEDFILEINFO(pointer.getValue());

			fileversion[0] = (short) (fileInfoStructure.dwFileVersionMS.longValue() >> 16);
			fileversion[1] = (short) (fileInfoStructure.dwFileVersionMS.longValue() & 0xffff);
			fileversion[2] = (short) (fileInfoStructure.dwFileVersionLS.longValue() >> 16);
			fileversion[3] = (short) (fileInfoStructure.dwFileVersionLS.longValue() & 0xffff);

			productversion[0] = (short) (fileInfoStructure.dwProductVersionMS.longValue() >> 16);
			productversion[1] = (short) (fileInfoStructure.dwProductVersionMS.longValue() & 0xffff);
			productversion[2] = (short) (fileInfoStructure.dwProductVersionLS.longValue() >> 16);
			productversion[3] = (short) (fileInfoStructure.dwProductVersionLS.longValue() & 0xffff);
		} finally {
			Kernel32.INSTANCE.GlobalFree(buffer);
		}

	}

	public int getChromeMajorVersion() {
		return productversion[0];
	}

}

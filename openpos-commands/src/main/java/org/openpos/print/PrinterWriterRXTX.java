package org.openpos.print;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.ParallelPort;
import gnu.io.SerialPort;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.openbravo.pos.printer.TicketPrinterException;
import com.openbravo.pos.printer.escpos.PrinterWritter;

public class PrinterWriterRXTX extends PrinterWritter {

	private boolean initialized = false;
	private ByteArrayOutputStream baos = new ByteArrayOutputStream(100 * 1024);

	private CommPortIdentifier m_PortIdPrinter;
	private CommPort m_CommPortPrinter;

	private String m_sPortPrinter;
	private OutputStream m_out;

	/** Creates a new instance of PrinterWritterComm */
	public PrinterWriterRXTX(String sPortPrinter) throws TicketPrinterException {
		m_sPortPrinter = sPortPrinter;
		m_out = null;
	}

	protected void daemonWrite(byte[] data) {
		try {
			if (m_out == null) {
				m_PortIdPrinter = CommPortIdentifier.getPortIdentifier(m_sPortPrinter); // Tomamos el puerto                   
				m_CommPortPrinter = m_PortIdPrinter.open("PORTID", 2000); // Abrimos el puerto       

				m_out = m_CommPortPrinter.getOutputStream(); // Tomamos el chorro de escritura   

				if (m_PortIdPrinter.getPortType() == CommPortIdentifier.PORT_SERIAL) {
					((SerialPort)m_CommPortPrinter).setSerialPortParams(115200, SerialPort.DATABITS_8,
							SerialPort.STOPBITS_1, SerialPort.PARITY_NONE); // Configuramos el puerto
				}
				else if (m_PortIdPrinter.getPortType() == CommPortIdentifier.PORT_PARALLEL) {
					((ParallelPort)m_CommPortPrinter).setMode(1);
				}
			}
			m_out.write(data);
		}
		catch (Exception e) {
			System.err.println(e);
		}
	}

	protected void daemonFlush() {
		//		try {
		//			if (m_out != null) {
		//				m_out.flush();
		//			}
		//		}
		//		catch (IOException e) {
		//			System.err.println(e);
		//		}
	}

	protected void daemonClose() {
		try {
			if (m_out != null) {
				m_out.flush();
				m_out.close();
				m_out = null;
				m_CommPortPrinter = null;
				m_PortIdPrinter = null;
			}
		}
		catch (IOException e) {
			System.err.println(e);
		}
	}

	private void localWrite(byte[] data) {
		try {
			baos.write(data);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void init(byte[] data) {
		if (!initialized) {
			localWrite(data);
			initialized = true;
		}
	}

	@Override
	public void write(byte[] data) {
		localWrite(data);
	}

	@Override
	public void write(String sValue) {
		localWrite(sValue.getBytes());
	}

	@Override
	public void flush() {
		daemonWrite(baos.toByteArray());
		daemonFlush();
		baos.reset();
	}

	@Override
	public void close() {
		daemonClose();
	}

	@Override
	protected void internalWrite(byte[] data) {

	}

	@Override
	protected void internalFlush() {

	}

	@Override
	protected void internalClose() {
	}
}

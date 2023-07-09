/*
 *    Copyright 2017-2021 Denis Kokorin, Oded Arbel
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.github.kokorin.jaffree.process;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * A re-usable handler used to forward input to stdin.
 */
public final class CommandSender {

    private BufferedWriter osw = null;

    /**
     * Set the current stdin OutputStream.
     *
     * @param outputStream the OutputStream to write to.
     */
    void setOutputStream(final OutputStream outputStream) {
        if (this.osw != null) {
            throw new IllegalStateException("CommandSender is already in use.");
        }
        this.osw = new BufferedWriter(new OutputStreamWriter(outputStream));
    }

    /**
     * Close the OutputStreamWriter if necessary.
     */
    void close() {
        if (osw == null) {
            return;
        }
        try {
            osw.close();
        } catch (IOException e) {
            return; // ignored
        } finally {
            this.osw = null;
        }
    }

    /**
     * Send data to stdin if the stream is open.
     *
     * @param command The data to send.
     * @return true when the data was written, false otherwise.
     */
    public boolean sendCommand(final String command) {
        final BufferedWriter currentOsw = this.osw;
        if (currentOsw == null) {
            return false;
        }

        try {
            currentOsw.write(command);
            currentOsw.flush();
        } catch (IOException e) {
            close();
            return false;
        }
        return true;
    }
}

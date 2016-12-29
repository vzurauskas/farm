/**
 * Copyright (c) 2016 Zerocracy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to read
 * the Software only. Permissions is hereby NOT GRANTED to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zerocracy.farm;

import com.jcabi.aspects.Async;
import com.zerocracy.jstk.Item;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Synchronized and thread safe item.
 *
 * @author Yegor Bugayenko (yegor256@gmail.com)
 * @version $Id$
 * @since 0.1
 */
final class SyncItem implements Item {

    /**
     * Original item.
     */
    private final Item origin;

    /**
     * Ctor.
     * @param item Original item
     */
    SyncItem(final Item item) {
        this.origin = item;
    }

    @Override
    public Path path() throws IOException {
        synchronized (this.origin) {
            return this.origin.path();
        }
    }

    @Override
    @Async
    public void close() throws IOException {
        synchronized (this.origin) {
            this.origin.close();
        }
    }
}

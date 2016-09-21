/*
 * Copyright (c) 2016 The Ontario Institute for Cancer Research. All rights reserved.                             
 *                                                                                                               
 * This program and the accompanying materials are made available under the terms of the GNU Public License v3.0.
 * You should have received a copy of the GNU General Public License along with                                  
 * this program. If not, see <http://www.gnu.org/licenses/>.                                                     
 *                                                                                                               
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY                           
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES                          
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT                           
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,                                
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED                          
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;                               
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER                              
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN                         
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.dcc.portal.test.hook;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import org.dcc.portal.test.hook.TakeDebug.TakeDebugHook;
import org.dcc.portal.test.hook.TakeDebug.TakeDebugOptions;
import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.components.ComponentInstantiator;
import org.fluentlenium.core.hook.BaseHook;
import org.fluentlenium.core.hook.Hook;
import org.fluentlenium.core.hook.HookOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import com.google.common.base.Supplier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Inherited
@Target({ ElementType.FIELD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Hook(TakeDebugHook.class)
@HookOptions(TakeDebugOptions.class)
public @interface TakeDebug {

  boolean htmlDump() default true;

  boolean screenShot() default true;

  @Slf4j
  class TakeDebugHook extends BaseHook<TakeDebugOptions> {

    public TakeDebugHook(FluentControl fluentControl, ComponentInstantiator instantiator,
        Supplier<WebElement> elementSupplier, Supplier<ElementLocator> locatorSupplier, TakeDebugOptions options) {
      super(fluentControl, instantiator, elementSupplier, locatorSupplier, options);
    }

    @Override
    public void click() {
      try {
        super.click();
      } catch (Exception e) {
        take(e);
        throw e;
      }
    }

    @Override
    public WebElement findElement() {
      try {
        return super.findElement();
      } catch (Exception e) {
        take(e);
        throw e;
      }
    }

    @Override
    public WebElement findElement(By arg0) {
      try {
        return super.findElement(arg0);
      } catch (Exception e) {
        take(e);
        throw e;
      }
    }

    @Override
    public List<WebElement> findElements() {
      try {
        return super.findElements();
      } catch (Exception e) {
        take(e);
        throw e;
      }
    }

    @Override
    public List<WebElement> findElements(By arg0) {
      try {
        return super.findElements(arg0);
      } catch (Exception e) {
        take(e);
        throw e;
      }
    }

    private void take(Exception e) {
      log.error("Error: {}", e.getMessage());
      if (getOptions().isHtmlDump()) {
        takeHtmlDump("take-debug-" + System.currentTimeMillis() + ".html");
      }
      if (getOptions().isScreenShot()) {
        takeScreenShot("take-debug-" + System.currentTimeMillis() + ".png");
      }
    }

  }

  @Getter
  @Setter
  @Builder
  @TakeDebug
  @AllArgsConstructor
  class TakeDebugOptions {

    boolean htmlDump;
    boolean screenShot;

    public TakeDebugOptions() {
      this(TakeDebugOptions.class.getAnnotation(TakeDebug.class));
    }

    public TakeDebugOptions(TakeDebug annotation) {
      this.htmlDump = annotation.htmlDump();
      this.screenShot = annotation.screenShot();
    }

  }

}
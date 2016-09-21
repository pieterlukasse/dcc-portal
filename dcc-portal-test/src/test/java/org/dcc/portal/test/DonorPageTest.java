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
package org.dcc.portal.test;

import static org.dcc.portal.test.util.DriverAssertions.assertThat;
import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;

import org.dcc.portal.test.hook.TakeDebug;
import org.dcc.portal.test.page.DonorPage;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;

import lombok.val;

@TakeDebug
public class DonorPageTest extends BaseTest {

  @Page
  DonorPage donorPage;

  @Test
  public void testDonorPage() {
    val do1 = donorPage.go("DO1");

    goTo("/team");
    assertThat(driver).hasNoErrors();

    assertThat(do1).isAt();

    assertThat(do1.summary()).isNotDisplayed();
    assertThat(do1.specimen()).isDisplayed();
    assertThat(do1.clinical()).isDisplayed();
  }

}
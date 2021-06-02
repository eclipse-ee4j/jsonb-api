/*
 * Copyright (c) 2017, 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

/*
 * $Id$
 */

package jakarta.json.bind.tck.defaultmapping.identifiers;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.json.bind.JsonbException;
import jakarta.json.bind.tck.MappingTester;
import jakarta.json.bind.tck.defaultmapping.identifiers.model.StringContainer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @test
 * @sources NamesAndIdentifiersMappingTest.java
 * @executeClass com.sun.ts.tests.jsonb.defaultmapping.identifiers.NamesAndIdentifiersMappingTest
 **/
public class NamesAndIdentifiersMappingTest {

  /*
   * @testName: testSimpleMapping
   *
   * @assertion_ids: JSONB:SPEC:JSB-3.15-1; JSONB:SPEC:JSB-3.15-2;
   * JSONB:SPEC:JSB-3.15-3
   *
   * @test_Strategy: Assert that java field name can be correctly mapped to json
   * identifier and vice versa
   */
  @Test
  public void testSimpleMapping() {
    new MappingTester<>(StringContainer.class).test("Test String", "\"Test String\"");
  }

  /*
   * @testName:
   * testSimpleMappingNoCorrespondingIdentifierWithFailOnUnknownProperties
   *
   * @assertion_ids: JSONB:SPEC:JSB-3.15-4
   *
   * @test_Strategy: Assert that error is reported if a Java identifier with
   * corresponding name as in json document cannot be found or is not accessible
   */
  @Test
  public void testSimpleMappingNoCorrespondingIdentifierWithFailOnUnknownProperties() {
      Jsonb jsonb = JsonbBuilder.create(new JsonbConfig().setProperty("jsonb.fail-on-unknown-properties", true));
      assertThrows(JsonbException.class,
                   () -> jsonb.fromJson("{ \"data\" : \"Test String\" }", StringContainer.class),
                   "A JsonbException is expected if a Java identifier with corresponding name as in json "
                           + "document cannot be found.");
  }
}

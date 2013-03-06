/**
 * Zillians MMO
 * Copyright (C) 2007-2010 Zillians.com, Inc.
 * For more information see http://www.zillians.com
 *
 * Zillians MMO is the library and runtime for massive multiplayer online game
 * development in utility computing model, which runs as a service for every
 * developer to build their virtual world running on our GPU-assisted machines.
 *
 * This is a close source library intended to be used solely within Zillians.com
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * COPYRIGHT HOLDER(S) BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN
 * AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
/**
 * @date May 25, 2010 maple - Initial version created.
 */

package zillians.api.debug
{
    // string
    @function_mapper { name="__zillians_api_debug_puts"; }
    function puts(var s:string):int32;
    @function_mapper { name="__zillians_api_debug_print_string"; }
    function print_string(var s:string):int32;

    // unsigned int
    @function_mapper { name="__zillians_api_debug_print_uint8"; }
    function print_uint8(var s:uint8):int32;
    @function_mapper { name="__zillians_api_debug_print_uint16"; }
    function print_uint16(var s:uint16):int32;
    @function_mapper { name="__zillians_api_debug_print_uint32"; }
    function print_uint32(var s:uint32):int32;
    @function_mapper { name="__zillians_api_debug_print_uint64"; }
    function print_uint64(var s:uint64):int32;

    // signed int
    @function_mapper { name="__zillians_api_debug_print_int8"; }
    function print_int8(var s:int8):int32;
    @function_mapper { name="__zillians_api_debug_print_int16"; }
    function print_int16(var s:int16):int32;
    @function_mapper { name="__zillians_api_debug_print_int32"; }
    function print_int32(var s:int32):int32;
    @function_mapper { name="__zillians_api_debug_print_int64"; }
    function print_int64(var s:int64):int32;

    // float
    @function_mapper { name="__zillians_api_debug_print_float32"; }
    function print_float32(var s:float32):int32;
    @function_mapper { name="__zillians_api_debug_print_float64"; }
    function print_float64(var s:float64):int32;

    // object
    @function_mapper { name="__zillians_api_debug_print_object"; }
    function print_object(var s:object):int32;
}
